package com.ssafy.userservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Builder
@Table(name = "members")
@ToString(of = {"id", "department", "zoneId"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue
    private Integer id;

    @Builder.Default
    @Column(columnDefinition = "INT DEFAULT 1")
    private Integer profile = 1;
//    @Column(nullable = false, length = 50, unique = true)
    private String department;
//    @Column(nullable = false, columnDefinition = "INT DEFAULT Asia/Seoul")
    private String zoneId;

    @MapsId
    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "id")
    private Auth auth;

}
