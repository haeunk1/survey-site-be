package com.project.survey.application.command.survey;

import com.project.survey.adapter.in.web.dto.request.survey.SurveyListRequestDto;
import com.project.survey.adapter.out.persistence.entity.constant.ListOrderType;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class FindSurveyListCommand {
    private String title;
    private ListOrderType orderType;

    // factory method
    public static FindSurveyListCommand of(SurveyListRequestDto surveyListRequestDto) {
        return FindSurveyListCommand.builder()
                .title(surveyListRequestDto.getTitle())
                .orderType(surveyListRequestDto.getOrderType())
                .build();
    }
}
