package com.clothes.damafashion.controller;

import com.clothes.damafashion.controller.dto.AuthDto;
import com.clothes.damafashion.controller.dto.TokenDto;
import com.clothes.damafashion.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Auth controller.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

  private final AuthenticationManager authenticationManager;
  private final TokenService tokenService;

  /**
   * Instantiates a new Auth controller.
   *
   * @param authenticationManager the authentication manager
   * @param tokenService          the token service
   */
  @Autowired
  public AuthController(AuthenticationManager authenticationManager, TokenService tokenService) {
    this.authenticationManager = authenticationManager;
    this.tokenService = tokenService;
  }

  /**
   * Login token dto.
   *
   * @param authDto the auth dto
   * @return the token dto
   */
  @PostMapping("/login")
  public TokenDto login(@RequestBody AuthDto authDto) {
    UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(
        authDto.username(), authDto.password());

    Authentication auth = authenticationManager.authenticate(usernamePassword);

    String token = tokenService.generateToken(auth.getName());
    return new TokenDto(token);
  }
}
