package com.project.survey.adapter.in.web.dto.response.survey;


import java.time.LocalDateTime;

import com.project.survey.adapter.out.persistence.entity.constant.SurveyType;

import lombok.*;
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class SurveyListResponseDto {
    private Long surveyId;
    private String name;
    private String title;
    private SurveyType type;
    private int perPoint;
    private int limitSubmit;
    private LocalDateTime startdate;
    private LocalDateTime enddate;
    private Long questionCnt;
    //private int submitCnt; //추가예정
    
}
