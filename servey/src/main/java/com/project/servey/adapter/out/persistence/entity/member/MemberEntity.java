package com.project.servey.adapter.out.persistence.entity.member;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name="Member")
public class MemberEntity {
    @Id
    @Column(name = "member_id") 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;               // PK

    @Column(unique = true, nullable = false)
    private String email;          // 이메일

    @Column(name = "password")
    private String password;       // 소셜 로그인 사용자는 NULL일 수 있음

    @Column(name = "name", nullable = false)
    private String name;           // 이름

    @CreationTimestamp
    @Column(name = "joined_date", nullable = false)
    private LocalDateTime joinedDate; // 가입일
}
