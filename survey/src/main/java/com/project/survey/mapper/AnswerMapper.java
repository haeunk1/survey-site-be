package com.project.survey.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import com.project.survey.adapter.out.persistence.entity.survey.AnswerEntity;
import com.project.survey.domain.Answer;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface AnswerMapper {
    List<AnswerEntity> domainsToEntities(List<Answer> list);
    List<Answer> entitiesToDomains(List<AnswerEntity> list);
}
