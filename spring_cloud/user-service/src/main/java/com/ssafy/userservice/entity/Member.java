package com.ssafy.userservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Builder
@Table(name = "members")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue
    private Integer id;

    @Builder.Default
    @Column(columnDefinition = "INT DEFAULT 1")
    private Integer profile = 1;
    private String nickname;
    @Column(nullable = false, length = 50, unique = true)
    private String email;
    @Column(nullable = false, unique = true)
    private String encryptedPwd;

    @MapsId
    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "id")
    private Auth auth;

}
