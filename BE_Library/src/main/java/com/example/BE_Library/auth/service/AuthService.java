package com.example.BE_Library.auth.service;

import com.example.BE_Library.auth.dto.request.LoginRequest;
import com.example.BE_Library.auth.dto.response.LoginResponse;
import com.example.BE_Library.auth.exception.OurException;
import com.example.BE_Library.auth.repository.AuthRepository;
import com.example.BE_Library.auth.utils.JWTUtils;
import com.example.BE_Library.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class AuthService implements IAuthService {

    @Autowired
    private AuthRepository authRepository;
    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        LoginResponse response = new LoginResponse();
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
            var user = authRepository.findByEmail(loginRequest.getEmail()).orElseThrow(() -> new OurException("User Not found"));

            var token = jwtUtils.generateToken(user);
            response.setStatusCode(200);
            response.setToken(token);
            response.setRole(user.getRole());
            response.setExpirationTime("7 Days");
            response.setMessage("successful");

        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error Occurred During User Login: " + e.getMessage());
            e.printStackTrace();
        }
        return response;
    }

    @Override
    @Transactional
    public String generateNewPassword(String email) {
        User user = authRepository.findByEmail(email).orElseThrow(() -> new OurException("User not found with email: " + email));

        String newRawPassword = UUID.randomUUID().toString().substring(0, 8);

        String encodedPassword = passwordEncoder.encode(newRawPassword);
        user.setPassword(encodedPassword);
        authRepository.save(user);

        return newRawPassword;
    }

}
