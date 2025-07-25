package com.sistemadepagamentosimplificado.controller;

import java.io.InvalidObjectException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistemadepagamentosimplificado.domain.user.User;
import com.sistemadepagamentosimplificado.dto.UserDTO;
import com.sistemadepagamentosimplificado.service.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/users")
public class UserController {
  @Autowired
  private UserService userService;

  @PostMapping
  public User createUser(@RequestBody UserDTO userDTO) throws InvalidObjectException {
    return userService.createUser(userDTO);
  }

  @GetMapping("/{id}")
  public UserDTO getUserById(@PathVariable Long id) throws Exception {
    return new UserDTO(userService.getUserById(id));
  }

  @GetMapping
  public List<UserDTO> getUsers() {
    List<UserDTO> users = userService.getUsers().stream()
                                                .map((user) -> new UserDTO(user))
                                                .collect(Collectors.toList());

    return users;
  }
}
