package com.sistemadepagamentosimplificado.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemadepagamentosimplificado.domain.user.User;

public interface UserRepository extends JpaRepository<User, Long> {
  public User findUserByIdentificationDocument(String identificationDocument);
}
