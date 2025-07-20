package com.sistemadepagamentosimplificado.dto;

import java.math.BigDecimal;

import com.sistemadepagamentosimplificado.domain.user.User;
import com.sistemadepagamentosimplificado.domain.user.UserType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
  private Long id;
  private String fullName;
  private String identificationDocument;
  private String email;
  private String password;
  private BigDecimal balance;
  private UserType userType;

  public UserDTO() { }

  public UserDTO(User user) {
    this.id = user.getId();
    this.fullName = user.getFullName();
    this.identificationDocument = user.getIdentificationDocument();
    this.email = user.getEmail();
    this.password = user.getPassword();
    this.balance = user.getBalance();
    this.userType = user.getUserType();
  }
}
