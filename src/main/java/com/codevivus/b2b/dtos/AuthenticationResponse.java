package com.codevivus.b2b.dtos;

import com.codevivus.b2b.enums.UserRole;
import lombok.Data;

@Data
public class AuthenticationResponse {

    private String jwt;

    private UserRole userRole;

    private Long UserId;

}
