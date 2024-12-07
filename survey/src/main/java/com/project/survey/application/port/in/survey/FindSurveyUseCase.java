package com.project.survey.application.port.in.survey;

import java.util.List;

import com.project.survey.adapter.in.web.dto.response.survey.SurveyListResponseDto;
import com.project.survey.adapter.in.web.dto.response.survey.SurveyResponseDto;
import com.project.survey.application.command.survey.FindSurveyCommand;
import com.project.survey.application.command.survey.FindSurveyListCommand;

public interface FindSurveyUseCase {
    SurveyResponseDto findSurvey(FindSurveyCommand findCommand);
    List<SurveyResponseDto> findSurveyAllList();
    List<SurveyListResponseDto> findSurveyFilteredList(FindSurveyListCommand findSurveyListCommand);
}
