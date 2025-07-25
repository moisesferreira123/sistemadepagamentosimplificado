package com.sistemadepagamentosimplificado.infra;

import java.io.InvalidObjectException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.sistemadepagamentosimplificado.dto.ExceptionDTO;

import jakarta.persistence.EntityNotFoundException;

@SuppressWarnings("rawtypes")
@RestControllerAdvice
public class ControllerExceptionHandler {
  @ExceptionHandler(InvalidObjectException.class)
  public ResponseEntity invalidObjectException(InvalidObjectException exception) {
    ExceptionDTO exceptionDTO = new ExceptionDTO(exception.getMessage());
    return ResponseEntity.badRequest().body(exceptionDTO);
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity duplicateEntry(DataIntegrityViolationException exception) {
    ExceptionDTO exceptionDTO = new ExceptionDTO("Existing user");
    return ResponseEntity.badRequest().body(exceptionDTO);
  }

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity entryNotFound(EntityNotFoundException exception) {
    return ResponseEntity.notFound().build();
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity generalException(Exception exception) {
    ExceptionDTO exceptionDTO = new ExceptionDTO(exception.getMessage());
    return ResponseEntity.internalServerError().body(exceptionDTO);
  }
}
