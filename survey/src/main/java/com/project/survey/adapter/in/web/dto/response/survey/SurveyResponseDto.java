package com.project.survey.adapter.in.web.dto.response.survey;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.survey.adapter.out.persistence.entity.constant.SurveyType;

import lombok.*;
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SurveyResponseDto {
    private Long surveyId;
    private Long memberId;
    private String title;
    private String description;
    private SurveyType type;
    private int perPoint;
    private int limitSubmit;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startdate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime enddate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String deleteYn;

    public static SurveyResponseDto of(Long memberId, String title, String description, SurveyType type
    , int perPoint, int limitSubmit, LocalDateTime startdate,LocalDateTime enddate){
        return builder()
        .memberId(memberId)
        .title(title)
        .description(description)
        .perPoint(perPoint)
        .type(type)
        .limitSubmit(limitSubmit)
        .startdate(startdate)
        .enddate(enddate)
        .createdAt(LocalDateTime.now())
        .deleteYn("N")
        .build();
    }

    public static SurveyResponseDto of(Long surveyId, Long memberId, String title, SurveyType type
    , int perPoint, int limitSubmit, LocalDateTime startdate,LocalDateTime enddate){
        return builder()
        .surveyId(surveyId)
        .memberId(memberId)
        .title(title)
        .perPoint(perPoint)
        .type(type)
        .limitSubmit(limitSubmit)
        .startdate(startdate)
        .enddate(enddate)
        .createdAt(LocalDateTime.now())
        .deleteYn("N")
        .build();
    }
}
