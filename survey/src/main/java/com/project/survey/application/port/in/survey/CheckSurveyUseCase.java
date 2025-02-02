package com.project.survey.application.port.in.survey;

public interface CheckSurveyUseCase {
    boolean checkSubmitStatus(Long surveyId, String token);
}
