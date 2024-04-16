package com.ssafy.userservice.dto;

import java.util.Date;
import lombok.Data;

@Data
public class MemberDto {
    private String email;
    private String name;
    private String pwd;
    private String memberId;
    private Date createdAt;
    private String decryptedPwd;
    private String encryptedPwd;
}
