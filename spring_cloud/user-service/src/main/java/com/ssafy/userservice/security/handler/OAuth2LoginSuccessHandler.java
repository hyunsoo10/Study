package com.ssafy.userservice.security.handler;

import com.ssafy.userservice.dto.AuthDto;
import com.ssafy.userservice.entity.Auth;
import com.ssafy.userservice.repository.AuthRepository;
import com.ssafy.userservice.security.jwt.JwtTokenDto;
import com.ssafy.userservice.security.jwt.JwtTokenProvider;
import com.ssafy.userservice.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtTokenProvider jwtTokenProvider;
    private final AuthRepository authRepository;
    private final AuthService authService;
    private final String CALLBACK_URL = "https://pickitup.online/main/auth/callback";

    @Override
    @Transactional
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
        Authentication authentication) throws IOException {

        JwtTokenDto tokenSet = jwtTokenProvider.generateToken(authentication);

        // DB에 Refreshtoken 저장
        AuthDto authDto = authService.getUserByUsername(authentication.getName());
        Auth updatedAuth = Auth.toDto(authDto);
        updatedAuth.setRefreshToken(tokenSet.getRefreshToken());
        User user = userQueryJpaRepository.findById(updatedAuth.getId()).orElseThrow(
            () -> new UsernameNotFoundException("해당 유저를 찾을 수 없습니다."));
        updatedAuth.setUser(user);
        authCommandService.increaseAttendCount(updatedAuth);
        updatedAuth.setLastLoginDate();
        authCommandJpaRepository.save(updatedAuth);

        // Redis에 Refreshtoken 저장
        RefreshToken refreshToken = RefreshToken.builder()
            .authId(updatedAuth.getId())
            .refreshToken(tokenSet.getRefreshToken())
            .build();
        redisService.saveRefreshToken(refreshToken.getAuthId(), refreshToken.getRefreshToken());
        // token 쿼리스트링
        String targetUrl = UriComponentsBuilder.fromUriString(CALLBACK_URL)
            .queryParam(JwtProperties.ACCESS_TOKEN, tokenSet.getAccessToken())
            .queryParam(JwtProperties.EXPRIES_IN, JwtProperties.ACCESS_TOKEN_EXPIRATION_TIME)
            .queryParam(JwtProperties.REFRESH_TOKEN, tokenSet.getRefreshToken())
            .build().toUriString();
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

}
