package com.example.kotlin2.security.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Builder
@Getter
@AllArgsConstructor
public class JwtTokenDto {

    private String accessToken;
    private String refreshToken;

    public Map<String, Object> responseDto() {
        Map<String, Object> result = new HashMap<>();
        result.put(JwtProperties.TOKEN_TYPE, JwtProperties.TOKEN_PREFIX.substring(0, 6));
        result.put(JwtProperties.ACCESS_TOKEN, this.getAccessToken());
        result.put(JwtProperties.REFRESH_TOKEN, this.getRefreshToken());
        result.put(JwtProperties.EXPRIES_IN, JwtProperties.ACCESS_TOKEN_EXPIRATION_TIME);
        return result;
    }
}
