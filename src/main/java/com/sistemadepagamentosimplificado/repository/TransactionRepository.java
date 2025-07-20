package com.sistemadepagamentosimplificado.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemadepagamentosimplificado.domain.transaction.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
  
}
