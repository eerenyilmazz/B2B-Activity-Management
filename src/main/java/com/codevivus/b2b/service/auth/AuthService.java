package com.codevivus.b2b.service.auth;

import com.codevivus.b2b.dtos.AuthenticationRequest;
import com.codevivus.b2b.dtos.AuthenticationResponse;
import com.codevivus.b2b.dtos.SignupRequest;
import com.codevivus.b2b.dtos.UserDto;

public interface AuthService {
    UserDto createUser(SignupRequest signupRequest);

    AuthenticationResponse authenticateUser(AuthenticationRequest authenticationRequest);
}