package com.project.survey.domain;

import java.time.LocalDateTime;

import com.project.survey.adapter.out.persistence.entity.constant.ListOrderType;
import com.project.survey.adapter.out.persistence.entity.constant.SurveyType;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Survey {
    private Long surveyId;
    private Long memberId;
    private String title;
    private String description;
    private SurveyType type;
    private int perPoint;
    private int limitSubmit;
    private LocalDateTime startdate;
    private LocalDateTime enddate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String deleteYn;

    private String name;
    private int questionCnt;
    //private int submitCnt; //추가예정

    // factory method
    public static Survey of(Long surveyId, Long memberId, String title, String description,
    SurveyType type, int perPoint, int limitSubmit, 
    LocalDateTime startdate, LocalDateTime enddate) {
        return Survey.builder()
                .memberId(memberId)
                .title(title)
                .title(title)
                .description(description)
                .perPoint(perPoint)
                .limitSubmit(limitSubmit)
                .startdate(startdate)
                .enddate(enddate)
                .createdAt(LocalDateTime.now())
                .updatedAt(null)
                .deleteYn("N")
                .build();
    }
}
