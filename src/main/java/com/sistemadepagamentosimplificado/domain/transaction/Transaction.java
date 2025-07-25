package com.sistemadepagamentosimplificado.domain.transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.sistemadepagamentosimplificado.domain.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TB_TRANSACTIONS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Transaction {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private BigDecimal value;

  @Column(nullable = false)
  private LocalDateTime transactionDate;

  @ManyToOne
  @JoinColumn(nullable = false)
  private User payer;

  @ManyToOne
  @JoinColumn(nullable = false)
  private User payee;
}
