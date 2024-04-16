package com.ssafy.userservice.service;

import com.ssafy.userservice.dto.AuthDto;

public interface AuthService {
    AuthDto getAuthByUsername(String username);
}
