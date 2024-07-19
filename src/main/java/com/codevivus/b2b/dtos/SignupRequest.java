package com.codevivus.b2b.dtos;

import lombok.Data;

@Data
public class SignupRequest {

    private String name;
    private String email;
    private String password;
    private String companyName;
    private String companyRole;
    private String companyWebsite;
    private String companySector;
    private String phone;
    private String address;
    private String about;
    private String keywords;
    private String searchKeywords;
    private String profileImage;
    private Long socialMediaId;
}