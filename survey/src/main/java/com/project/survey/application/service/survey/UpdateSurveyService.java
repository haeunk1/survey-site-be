package com.project.survey.application.service.survey;

import org.springframework.transaction.annotation.Transactional;

import com.project.survey.application.command.survey.UpdateSurveyCommand;
import com.project.survey.application.port.in.survey.UpdateSurveyUseCase;
import com.project.survey.application.port.out.survey.FindSurveyPort;
import com.project.survey.application.port.out.survey.UpdateSurveyPort;
import com.project.survey.domain.Survey;
import com.project.survey.exception.ErrorCode;
import com.project.survey.exception.SurveyException;
import com.project.survey.mapper.SurveyMapper;
import com.project.survey.util.custom.UseCase;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = false)
@UseCase
public class UpdateSurveyService implements UpdateSurveyUseCase{
    
    private final UpdateSurveyPort updateSurveyPort;
    private final FindSurveyService findService;
    private final SurveyMapper surveyMapper;

    @Override
    public Long updateSurvey(UpdateSurveyCommand command) {
        Survey survey = surveyMapper.commandToDomain(command);

        //삭제된 게시글이 아닌지 검증
        findService.checkIsSurveyExist(survey.getSurveyId());
        return updateSurveyPort.updateSurvey(survey);
    }
}
