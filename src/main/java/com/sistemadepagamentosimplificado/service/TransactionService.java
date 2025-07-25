package com.sistemadepagamentosimplificado.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sistemadepagamentosimplificado.domain.transaction.Transaction;
import com.sistemadepagamentosimplificado.domain.user.User;
import com.sistemadepagamentosimplificado.domain.user.UserType;
import com.sistemadepagamentosimplificado.dto.TransactionDTO;
import com.sistemadepagamentosimplificado.repository.TransactionRepository;

@Service
public class TransactionService {
  @Autowired
  private TransactionRepository transactionRepository;

  @Autowired
  private UserService userService;

  @Autowired
  RestTemplate restTemplate;

  @Autowired
  NotificationService notificationService;

  public Transaction createTransaction(TransactionDTO transactionDTO) throws Exception {
    User payer = userService.getUserById(transactionDTO.payerId());
    User payee = userService.getUserById(transactionDTO.payeeId());

    if(payer.getUserType() == UserType.SHOPKEEPER) {
      throw new Exception("Shopkeepers can't send money to anyone");
    }

    if(transactionDTO.value().compareTo(BigDecimal.ZERO) <= 0) {
      throw new Exception("Transfer amount must be positive");
    }

    if(payer.getBalance().compareTo(transactionDTO.value()) < 0) {
      throw new Exception("User does not have sufficient balance");
    }

    if(!authorizeTransaction()) {
      throw new Exception("Unauthorized transaction");
    } 

    Transaction transaction = new Transaction();
    transaction.setPayer(payer);
    transaction.setPayee(payee);
    transaction.setValue(transactionDTO.value());
    transaction.setTransactionDate(LocalDateTime.now());

    payer.setBalance(payer.getBalance().subtract(transactionDTO.value()));
    payee.setBalance(payee.getBalance().add(transactionDTO.value()));

    transactionRepository.save(transaction);
    try {
      notificationService.sendNotification(payer.getEmail(), "Payment of " + transaction.getValue() + " was made successfully");
      notificationService.sendNotification(payee.getEmail(), "You have successfully received payment of " + transaction.getValue());
    } catch(Exception e) {
      throw new Exception("Notification service unavailable");
    }

    return transaction;
  }

  @SuppressWarnings({ "unchecked", "rawtypes" })
  private boolean authorizeTransaction() {
    try {
      ResponseEntity<Map> authorizationResponse = restTemplate.getForEntity("https://util.devi.tools/api/v2/authorize", Map.class);

      if(authorizationResponse.getStatusCode() == HttpStatus.OK) {
        Map<String, Boolean> data = (Map<String, Boolean>) authorizationResponse.getBody().get("data");
        return data.get("authorization") == true;
      }
    } catch(Exception e) {
      return false;
    }

    return false;
  }
}
