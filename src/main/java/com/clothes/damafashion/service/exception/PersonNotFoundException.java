package com.clothes.damafashion.service.exception;

/**
 * Exception for when a person is not found.
 */
public class PersonNotFoundException extends RuntimeException {

  public PersonNotFoundException() {
    super("Pessoa não encontrada!");
  }

}
