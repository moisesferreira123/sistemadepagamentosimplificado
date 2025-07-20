package com.sistemadepagamentosimplificado.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemadepagamentosimplificado.repository.TransactionRepository;

@Service
public class TransactionService {
  @Autowired
  private TransactionRepository transactionRepository;

  public void createTransaction() {
    
  }
}
