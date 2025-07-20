package com.sistemadepagamentosimplificado.domain.user;

import java.math.BigDecimal;

import com.sistemadepagamentosimplificado.dto.UserDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String fullName;

  @Column(nullable = false, unique = true)
  private String identificationDocument;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false)
  private String password;

  @Column
  private BigDecimal balance;

  @Enumerated(EnumType.STRING)
  private UserType userType;

  public User(UserDTO user) {
    this.fullName = user.getFullName();
    this.identificationDocument = user.getIdentificationDocument();
    this.email = user.getEmail();
    this.password = user.getPassword();
    this.balance = user.getBalance();
    this.userType = user.getUserType();
  }
}
