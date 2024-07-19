package com.codevivus.b2b.controller;

import com.codevivus.b2b.dtos.AuthenticationRequest;
import com.codevivus.b2b.dtos.AuthenticationResponse;
import com.codevivus.b2b.dtos.SignupRequest;
import com.codevivus.b2b.dtos.UserDto;
import com.codevivus.b2b.service.auth.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> signupUser(@RequestBody SignupRequest signupRequest) {
        UserDto createdUserDto = authService.createUser(signupRequest);
        return ResponseEntity.ok(createdUserDto);
    }

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {
        AuthenticationResponse response = authService.authenticateUser(authenticationRequest);
        return ResponseEntity.ok(response);
    }
}