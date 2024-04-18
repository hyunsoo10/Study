package com.ssafy.userservice.dto;

import java.util.Date;

import com.ssafy.userservice.entity.Member;
import lombok.*;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberDto {
    private Integer id;
    private String department;
    private String zoneId;

    public static MemberDto getMember(Member member) {
        return MemberDto.builder()
                .id(member.getId())
                .department(member.getDepartment())
                .zoneId(member.getZoneId())
                .build();
    }

}
