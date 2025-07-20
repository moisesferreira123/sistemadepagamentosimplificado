package com.sistemadepagamentosimplificado.service;

import java.io.InvalidObjectException;
import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemadepagamentosimplificado.domain.user.User;
import com.sistemadepagamentosimplificado.dto.UserDTO;
import com.sistemadepagamentosimplificado.repository.UserRepository;

@Service
public class UserService {
  @Autowired
  private UserRepository userRepository;

  public User createUser(UserDTO userDTO) throws InvalidObjectException {
    isValidDTOToCreation(userDTO);
    User newUser = new User(userDTO);
    return this.userRepository.save(newUser);
  }

  public User getUserById(Long id) throws Exception {
    return this.userRepository.findById(id).orElseThrow(() -> new Exception("ID do usuário não encontrado"));
  }

  private void isValidDTOToCreation(UserDTO userDTO) throws InvalidObjectException {
    if(userDTO.getFullName() == null || userDTO.getFullName().isEmpty()) {
      throw new InvalidObjectException("Full name can't be empty");
    }

    if(userDTO.getIdentificationDocument() == null || userDTO.getIdentificationDocument().isEmpty()) {
      throw new InvalidObjectException("Identification document can't be empty");
    }

    if(userDTO.getEmail() == null || userDTO.getEmail().isEmpty()) {
      throw new InvalidObjectException("Email can't be empty");
    }

    if(userDTO.getPassword() == null || userDTO.getPassword().isEmpty()) {
      throw new InvalidObjectException("Password can't be empty");
    }

    if(userDTO.getBalance() == null) {
      userDTO.setBalance(BigDecimal.ZERO);
    }

    if(userDTO.getUserType() == null) {
      throw new InvalidObjectException("Type user can't be empty");
    }
  }
}
