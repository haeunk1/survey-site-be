package com.project.survey.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.project.survey.adapter.in.web.dto.response.question.QuestionResponseDto;
import com.project.survey.adapter.out.persistence.entity.survey.QuestionEntity;
import com.project.survey.domain.Question;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface QuestionMapper {
    List<QuestionEntity> domainsToEntities(List<Question> questionDomains);

    List<QuestionResponseDto> domainToResponseDto(List<Question> questionDomains);

    List<Question> entitiesToDomains(List<QuestionEntity> entitities);

    QuestionEntity domaintoEntity(Question question);
    
}
