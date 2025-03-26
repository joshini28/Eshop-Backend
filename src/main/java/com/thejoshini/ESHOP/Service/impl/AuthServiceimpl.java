package com.thejoshini.ESHOP.Service.impl;

import com.thejoshini.ESHOP.entity.Role;
import com.thejoshini.ESHOP.entity.User;
import com.thejoshini.ESHOP.exception.AuthException;
import com.thejoshini.ESHOP.payload.LoginDto;
import com.thejoshini.ESHOP.payload.RegisterDto;
import com.thejoshini.ESHOP.repository.RoleRepository;
import com.thejoshini.ESHOP.repository.UserRepository;
import com.thejoshini.ESHOP.security.JwtTokenProvider;
import com.thejoshini.ESHOP.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceimpl implements AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Override
    public String login(LoginDto loginDto) {
        Authentication authentication=authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(),loginDto.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token=jwtTokenProvider.generateToken(authentication);
        System.out.println(token);
        return token;
    }

    @Override
    public String register(RegisterDto registerDto) {
        if(userRepository.existsByUsername(registerDto.getUsername())){
            throw new AuthException(HttpStatus.BAD_REQUEST, "Username is already taken!");
        }


        if(userRepository.existsByEmail(registerDto.getEmail())){
            throw new AuthException(HttpStatus.BAD_REQUEST, "Email is already taken!");
        }
        User user = new User();
        user.setName(registerDto.getName());
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role role = roleRepository.findByName("Admin").get();
        roles.add(role);
        user.setRoles(roles);


        userRepository.save(user);
        return "User registered successfully!";
    }
}
