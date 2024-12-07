package com.project.survey.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.project.survey.adapter.in.web.dto.response.survey.SurveyListResponseDto;
import com.project.survey.adapter.in.web.dto.response.survey.SurveyResponseDto;
import com.project.survey.adapter.out.persistence.entity.survey.SurveyEntity;
import com.project.survey.application.command.survey.CreateSurveyCommand;
import com.project.survey.application.command.survey.UpdateSurveyCommand;
import com.project.survey.domain.Survey;
//객체 간 매핑을 자동으로 처리
@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface SurveyMapper {

    Survey entityToDomain(SurveyEntity surveyEntity);

    SurveyResponseDto domainToResponseDto(Survey survey);

    SurveyEntity domainToEntity(Survey survey);

    Survey commandToDomain(CreateSurveyCommand command);
    
    Survey commandToDomain(UpdateSurveyCommand command);

    CreateSurveyCommand domainToConnCommand(Survey survey);

    List<Survey> entitiesToDomains(List<SurveyEntity> surveyEntities);

    List<SurveyResponseDto> domainsToResponseDtos(List<Survey> surveys);

    List<SurveyListResponseDto> domainsToListResponseDtos(List<Survey> servyes);

    //Survey commandToDomain(UpdateSurveyCommand command);

}