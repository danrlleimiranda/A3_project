package com.clothes.damafashion.controller;

import com.clothes.damafashion.controller.dto.UserCreationDto;
import com.clothes.damafashion.controller.dto.UserDto;
import com.clothes.damafashion.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type User controller.
 */
@RestController
@RequestMapping("/users")
public class UserController {

  private final UserService userService;

  /**
   * Instantiates a new User controller.
   *
   * @param userService the user service
   */
  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }


  /**
   * Create user dto.
   *
   * @param user the user
   * @return the user dto
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public UserDto createUser(@RequestBody UserCreationDto user) {
    return UserDto.fromEntity(userService.create(user.toEntity()));
  }
}
