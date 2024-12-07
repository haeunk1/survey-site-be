package com.project.survey.adapter.out.persistence.entity.survey;

import java.time.LocalDateTime;
import com.project.survey.adapter.out.persistence.entity.BaseEntity;
import com.project.survey.adapter.out.persistence.entity.constant.SurveyType;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name="Survey")
public class SurveyEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "survey_id") 
    private Long surveyId;          // PK

    @Column(name = "member_id", nullable = false)
    private Long memberId;          // 작성자

    @Column(name = "title", nullable = false)
    private String title;           // 제목

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private SurveyType type;       // 설문조사유형

    @Column(name = "per_point", nullable = false)
    private int perPoint;       

    @Column(name = "limit_submit", nullable = false)
    private int limitSubmit;      

    @Column(name = "startdate", nullable = false)
    private LocalDateTime startdate;

    @Column(name = "enddate", nullable = false)
    private LocalDateTime enddate;

    @Column(name = "delete_yn", nullable = false)
    private String deleteYn;

    //ID로 SurveyEntity 객체를 생성하는 정적 팩토리 메서드
    public static SurveyEntity of(Long surveyId){
        return SurveyEntity.builder()
        .surveyId(surveyId).build();
    }
}
