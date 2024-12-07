package com.project.survey.adapter.out.persistence.adapter;

import java.util.List;

import com.project.survey.adapter.out.persistence.entity.survey.AnswerEntity;
import com.project.survey.adapter.out.persistence.repository.AnswerRepository;
import com.project.survey.application.port.out.answer.CreateAnswerPort;
import com.project.survey.domain.Answer;
import com.project.survey.mapper.AnswerMapper;
import com.project.survey.util.custom.PersistenceAdapter;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@PersistenceAdapter
public class AnswerPersistenceAdapter implements CreateAnswerPort{
    private final AnswerRepository repository;
    private final AnswerMapper mapper;

    @Override
    public List<Answer> createAnswer(List<Answer> list) {
        List<AnswerEntity> entityList = mapper.domainsToEntities(list);
        List<AnswerEntity> rtnEntityList = repository.saveAll(entityList);
        return mapper.entitiesToDomains(rtnEntityList);
    }
    
}
