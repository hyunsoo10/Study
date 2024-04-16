package com.ssafy.userservice.dto;

import java.util.Date;

import com.ssafy.userservice.entity.Member;
import lombok.*;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberDto {
    private String email;
    private String nickname;
    private String pwd;
    private Date createdAt;
    private String decryptedPwd;
    private String encryptedPwd;

    public static MemberDto getMember(Member member) {
        return MemberDto.builder()
                .email(member.getEmail())
                .nickname(member.getNickname())
                .build();
    }

}
