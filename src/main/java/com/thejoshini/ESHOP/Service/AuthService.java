package com.thejoshini.ESHOP.Service;

import com.thejoshini.ESHOP.payload.LoginDto;
import com.thejoshini.ESHOP.payload.RegisterDto;

public interface AuthService {
    String login(LoginDto loginDto);
    String register(RegisterDto registerDto);
}


