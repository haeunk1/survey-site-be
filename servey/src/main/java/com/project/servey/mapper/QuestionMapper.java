package com.project.servey.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.project.servey.adapter.in.web.dto.response.question.QuestionResponseDto;
import com.project.servey.adapter.out.persistence.entity.servey.QuestionEntity;
import com.project.servey.domain.Question;

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
