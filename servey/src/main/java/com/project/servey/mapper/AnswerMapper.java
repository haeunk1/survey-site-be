package com.project.servey.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import com.project.servey.adapter.out.persistence.entity.servey.AnswerEntity;
import com.project.servey.domain.Answer;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface AnswerMapper {
    List<AnswerEntity> domainsToEntities(List<Answer> list);
    List<Answer> entitiesToDomains(List<AnswerEntity> list);
}
