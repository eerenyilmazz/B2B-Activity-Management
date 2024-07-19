package com.codevivus.b2b.service.implemantation;

import com.codevivus.b2b.dtos.AuthenticationRequest;
import com.codevivus.b2b.dtos.AuthenticationResponse;
import com.codevivus.b2b.dtos.SignupRequest;
import com.codevivus.b2b.dtos.UserDto;
import com.codevivus.b2b.entity.User;
import com.codevivus.b2b.enums.UserRole;
import com.codevivus.b2b.repository.UserRepository;
import com.codevivus.b2b.service.auth.AuthService;
import com.codevivus.b2b.utils.JwtUtil;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;
import org.modelmapper.ModelMapper;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;
    private final String adminEmail;
    private final String adminPassword;
    private final ModelMapper modelMapper;

    public AuthServiceImpl(UserRepository userRepository,
                           BCryptPasswordEncoder passwordEncoder,
                           AuthenticationManager authenticationManager,
                           UserDetailsService userDetailsService,
                           JwtUtil jwtUtil,
                           @Value("${admin.email}") String adminEmail,
                           @Value("${admin.password}") String adminPassword) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
        this.adminEmail = adminEmail;
        this.adminPassword = adminPassword;
        this.modelMapper = new ModelMapper();
    }

    @PostConstruct
    public void createAdminAccount() {
        User adminAccount = userRepository.findByUserRole(UserRole.ADMIN);
        if (adminAccount == null) {
            User user = new User();
            user.setEmail(adminEmail);
            user.setPassword(passwordEncoder.encode(adminPassword));
            user.setUserRole(UserRole.ADMIN);
            userRepository.save(user);
        }
    }

    @Override
    public UserDto createUser(SignupRequest signupRequest) {
        User user = new User();
        user.setName(signupRequest.getName());
        user.setEmail(signupRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        user.setUserRole(UserRole.USER);
        user.setAbout(signupRequest.getAbout());
        user.setSocialMediaId(signupRequest.getSocialMediaId());
        user.setPhone(signupRequest.getPhone());
        user.setKeywords(signupRequest.getKeywords());
        user.setProfileImage(signupRequest.getProfileImage());
        user.setAddress(signupRequest.getAddress());
        user.setSearchKeywords(signupRequest.getSearchKeywords());
        user.setCompanyWebsite(signupRequest.getCompanyWebsite());

        User createdUser = userRepository.save(user);

        return modelMapper.map(createdUser,UserDto.class);
    }


    @Override
    public AuthenticationResponse authenticateUser(AuthenticationRequest authenticationRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),
                            authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException | DisabledException e) {
            throw new RuntimeException("Invalid credentials or user is disabled", e);
        }

        UserDetails userDetails;
        try {
            userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
        } catch (UsernameNotFoundException e) {
            throw new RuntimeException("User not found", e);
        }

        String jwtToken = generateToken(userDetails.getUsername());
        Optional<User> optionalUser = userRepository.findFirstByEmail(userDetails.getUsername());

        AuthenticationResponse response = new AuthenticationResponse();
        response.setJwt(jwtToken);
        optionalUser.ifPresent(user -> {
            response.setUserId(user.getId());
            response.setUserRole(user.getUserRole());
        });

        return response;
    }

    private String generateToken(String username) {
        return jwtUtil.generateToken(username);
    }

}
