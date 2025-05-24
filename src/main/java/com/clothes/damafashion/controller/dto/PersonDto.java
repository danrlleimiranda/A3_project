package com.clothes.damafashion.controller.dto;


import com.clothes.damafashion.entity.Person;
import com.clothes.damafashion.security.Role;

/**
 * The type Person dto.
 */
public record PersonDto(Long id, String username, Role role) {

  /**
   * From entity person dto.
   *
   * @param person the person
   * @return the person dto
   */
  public static PersonDto fromEntity(Person person) {
    return new PersonDto(person.getId(), person.getUsername(), person.getRole());
  }
}
