package com.project.survey.application.command.survey;

import com.project.survey.exception.ErrorCode;

import com.project.survey.exception.SurveyException;

import lombok.*;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Builder
@Getter
public class FindSurveyCommand {
    private Long surveyId;

    // factory method
    public static FindSurveyCommand of(Long surveyId) {
        if (surveyId == null) {
            throw new SurveyException(ErrorCode.Survey_ID_IS_NULL);
        }

        return FindSurveyCommand.builder()
                .surveyId(surveyId)
                .build();
    }
}
