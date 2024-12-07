package com.project.survey.application.port.in.survey;

import com.project.survey.adapter.in.web.dto.response.survey.SurveyResponseDto;
import com.project.survey.application.command.survey.CreateSurveyCommand;

public interface CreateSurveyUseCase {
    SurveyResponseDto createSurvey(CreateSurveyCommand command);
} 
