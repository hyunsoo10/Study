package com.ssafy.userservice.service;

import com.ssafy.userservice.dto.AuthDto;
import com.ssafy.userservice.entity.Auth;
import com.ssafy.userservice.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final AuthRepository authRepository;

    @Override
    public AuthDto getAuthByUsername(String username) {
        Auth auth = authRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException(
                "Can't find user with this username. -> " + username));
        return AuthDto.getAuth(auth);
    }
}
