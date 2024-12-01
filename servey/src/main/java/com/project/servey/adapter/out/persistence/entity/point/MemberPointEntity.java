package com.project.servey.adapter.out.persistence.entity.point;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name="member_point")
public class MemberPointEntity {
    @Id
    @Column(name = "member_id") 
    private Long memberId;

    @Column(name = "current_point", nullable = false)
    private String currentPoint;          // 현재 포인트
}
