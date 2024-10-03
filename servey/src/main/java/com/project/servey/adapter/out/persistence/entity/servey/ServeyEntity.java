package com.project.servey.adapter.out.persistence.entity.servey;

import java.time.LocalDateTime;
import java.util.Objects;

import org.springframework.data.annotation.LastModifiedDate;

import com.project.servey.adapter.out.persistence.entity.BaseEntity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
//@NoArgsConstructor 기본생성자
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name="Servey")
public class ServeyEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long serveyId;          // PK

    @Column(name = "user-id", nullable = false)
    private String userId;          // 작성자

    @Column(name = "title", nullable = false)
    private String title;           // 제목

    @Column(name = "type", nullable = false)
    private int type;           // 설문조사유형

    @Column(name = "category", nullable = false)
    private int category;       // 카테고리

    @Column(name = "per-point", nullable = false)
    private int perPoint;       

    @Column(name = "limit-submit", nullable = false)
    private int limitSubmit;      

    @Column(name = "startdate", nullable = false)
    private LocalDateTime startdate;

    @Column(name = "enddate", nullable = false)
    private LocalDateTime enddate;

    //ID로 ServeyEntity 객체를 생성하는 정적 팩토리 메서드
    public static ServeyEntity of(Long serveyId){
        return ServeyEntity.builder()
        .serveyId(serveyId).build();
    }
}
