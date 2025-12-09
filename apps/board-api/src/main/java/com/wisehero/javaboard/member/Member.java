package com.wisehero.javaboard.member;

import com.wisehero.javaboard.config.db.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "member")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String loginId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickName;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private MemberRole role;

    @Builder
    public Member(String loginId, String password, String nickName, MemberRole role) {
        this.loginId = loginId;
        this.password = password;
        this.nickName = nickName;
        this.role = role;
    }
}
