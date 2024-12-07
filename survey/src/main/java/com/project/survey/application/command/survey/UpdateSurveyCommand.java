package com.project.survey.application.command.survey;

import java.time.LocalDateTime;

import com.project.survey.adapter.in.web.dto.response.survey.SurveyResponseDto;
import com.project.survey.adapter.out.persistence.entity.constant.SurveyType;

import lombok.*;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class UpdateSurveyCommand {
    private Long surveyId;
    private String title;
    private SurveyType type;
    private int perPoint;
    private int limitSubmit;
    private LocalDateTime startdate;
    private LocalDateTime enddate;

    // factory method
    public static UpdateSurveyCommand of(SurveyResponseDto surveyResponseDto) {
        return UpdateSurveyCommand.builder()
                .surveyId(surveyResponseDto.getSurveyId())
                .title(surveyResponseDto.getTitle())
                .type(surveyResponseDto.getType())
                .perPoint(surveyResponseDto.getPerPoint())
                .limitSubmit(surveyResponseDto.getLimitSubmit())
                .startdate(surveyResponseDto.getStartdate())
                .enddate(surveyResponseDto.getEnddate())
                .build();
    }
}
