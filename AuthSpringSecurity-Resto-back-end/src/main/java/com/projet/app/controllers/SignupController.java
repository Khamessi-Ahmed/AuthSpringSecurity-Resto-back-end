package com.projet.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projet.app.dto.AdminRegisterDto;
import com.projet.app.dto.ChefRegisterDto;
import com.projet.app.dto.EtudiantRegisterDto;
import com.projet.app.model.UserEntity;
import com.projet.app.services.AuthService;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/signup")
public class SignupController {
    
    private final AuthService authService;
    
    @Autowired
    public SignupController(AuthService authService) {
        this.authService = authService;
    }
    
    @PostMapping("/etudiant")
    @Transactional
    public ResponseEntity<?> signupEtudiant(@RequestBody EtudiantRegisterDto registerDto) {
        UserEntity createdUser = authService.createUser(registerDto);
        if (createdUser != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create user");
        }
    }
    
    @PostMapping("/chef")
    @Transactional
    public ResponseEntity<?> signupChef(@RequestBody ChefRegisterDto registerDto) {
        UserEntity createdUser = authService.createUser(registerDto);
        if (createdUser != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create user");
        }
    }
    
    @PostMapping("/admin")
    @Transactional
    public ResponseEntity<?> signupAdmin(@RequestBody AdminRegisterDto registerDto) {
        UserEntity createdUser = authService.createUser(registerDto);
        if (createdUser != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create user");
        }
    }
}
