package com.clothes.damafashion.security;

/**
 * Enum representing a Role.
 */
public enum Role {
  ADMIN("ROLE_ADMIN"),
  USER("ROLE_USER");

  private final String name;

  Role(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}