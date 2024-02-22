package com.example.kotlin2.service;

import com.example.kotlin2.domain.user.Role;
import com.example.kotlin2.domain.user.User;
import com.example.kotlin2.domain.user.UserRepository;
import com.example.kotlin2.presentation.dto.ApiResponse;
import com.example.kotlin2.presentation.dto.user.LoginRequestDto;
import com.example.kotlin2.presentation.dto.user.UserDto;
import com.example.kotlin2.presentation.dto.user.UserResponseDto;
import com.example.kotlin2.presentation.dto.user.UserSignDto;
import com.example.kotlin2.security.jwt.JwtTokenDto;
import com.example.kotlin2.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final UserService userService;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(UserRepository userRepository, UserService userService, AuthenticationManagerBuilder authenticationManagerBuilder, JwtTokenProvider jwtTokenProvider, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

    public ApiResponse<?> signUp(UserSignDto userSignDto) {
        User newUser = new User(
                userSignDto.getUserId(),
                userSignDto.getName(),
                passwordEncoder.encode(userSignDto.getPassword()),
                userSignDto.getEmail(),
                Role.USER,
                userSignDto.getAge()
        );
        userRepository.save(newUser);
        UserResponseDto userResponseDto = UserResponseDto.toDto(newUser);
        return new ApiResponse<>(userResponseDto, "회원가입 성공");
    }

    public JwtTokenDto login(LoginRequestDto loginRequestDto) {
        String userId = loginRequestDto.getUserId();
        String password = loginRequestDto.getPassword();

        //id로 유저 찾아오기
        User user = userRepository.findByUserId(userId);

        //비밀 번호 일치 여부 확인
        validatePassword(new UserDto(userId, password));

        // Login ID/PW를 기반으로 Authentication Token 생성
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                = new UsernamePasswordAuthenticationToken(userId, password);

        // 실제로 검증이 이루어지는 부분
        Authentication authentication =
                authenticationManagerBuilder.getObject()
                        .authenticate(usernamePasswordAuthenticationToken);

        // 인증 정보를 기반으로 JWT 토큰 생성
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtTokenProvider.generateToken(authentication);
    }

    public void validatePassword(UserDto userDto) {
        String userId = userDto.getUserId();
        String password = userDto.getPassword();
        User user = userRepository.findByUserId(userId);
        passwordEncoder.matches(password, user.getPassword());
    }
}
