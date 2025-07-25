package com.sistemadepagamentosimplificado.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistemadepagamentosimplificado.domain.transaction.Transaction;
import com.sistemadepagamentosimplificado.dto.TransactionDTO;
import com.sistemadepagamentosimplificado.service.TransactionService;

@RestController
@RequestMapping("api/transactions")
public class TransactionController {
  @Autowired
  private TransactionService transactionService;

  @PostMapping
  public Transaction createTransaction(@RequestBody TransactionDTO transactionDTO) throws Exception {
    return transactionService.createTransaction(transactionDTO);
  }
}
