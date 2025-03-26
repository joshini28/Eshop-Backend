package com.thejoshini.ESHOP.controller;

import com.thejoshini.ESHOP.Service.AuthService;
import com.thejoshini.ESHOP.payload.LoginDto;
import com.thejoshini.ESHOP.payload.RegisterDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/auth")
@RestController
public class AuthController {

    @Autowired
    private AuthService authService;
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
        System.out.println("hellothere");
        String message = authService.register(registerDto);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto){
        System.out.println("login service");
        String message = authService.login(loginDto);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}
