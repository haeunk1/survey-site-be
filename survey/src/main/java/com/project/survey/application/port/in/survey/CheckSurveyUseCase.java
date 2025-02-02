package com.project.survey.application.port.in.survey;

import com.project.survey.adapter.in.web.dto.response.survey.SurveySubmitStatusResponseDto;

public interface CheckSurveyUseCase {
    SurveySubmitStatusResponseDto checkSubmitStatus(Long surveyId, String token);
}
