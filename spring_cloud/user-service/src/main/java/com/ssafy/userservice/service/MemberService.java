package com.ssafy.userservice.service;

import com.ssafy.userservice.dto.MemberDto;
import com.ssafy.userservice.entity.Auth;

public interface MemberService {
    MemberDto createMember(MemberDto memberDto);

    MemberDto creteMember(Auth auth);
}
