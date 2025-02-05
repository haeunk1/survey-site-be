package com.project.survey.application.service.survey;

import com.project.survey.adapter.in.web.dto.response.survey.SurveyResponseDto;
import com.project.survey.application.command.survey.CreateSurveyCommand;
import com.project.survey.application.port.in.survey.CreateSurveyUseCase;
import com.project.survey.application.port.out.survey.CreateSurveyPort;
import com.project.survey.domain.Survey;
import com.project.survey.mapper.SurveyMapper;
import com.project.survey.util.custom.UseCase;

import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;


@UseCase
@RequiredArgsConstructor
@Transactional(readOnly = false)
public class CreateSurveyService implements CreateSurveyUseCase{
    private final CreateSurveyPort createSurveyPort;
    private final SurveyMapper surveyMapper;


    /**
     * @param command 설문조사 생성 요청
     * @return 설문조사 생성 결과
     * @apiNote 설문조사 생성
     */
    @Transactional
    @Override
    public SurveyResponseDto createSurvey(CreateSurveyCommand command) {
        
        Survey survey = surveyMapper.commandToDomain(command);
        Survey createdSurvey = createSurveyPort.createSurvey(survey);

        return surveyMapper.domainToResponseDto(createdSurvey);
    }    
    
}
