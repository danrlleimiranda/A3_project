package com.clothes.damafashion.controller.dto;


import com.clothes.damafashion.entity.Person;
import com.clothes.damafashion.security.Role;

/**
 * The type Person creation dto.
 */
public record PersonCreationDto(String username, String password, Role role) {

  /**
   * To entity person.
   *
   * @return the person
   */
  public Person toEntity() {
    return new Person(username, password, role);
  }
}
