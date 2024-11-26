package com.project.servey.adapter.out.persistence.adapter;

import java.util.List;

import com.project.servey.adapter.out.persistence.entity.servey.AnswerEntity;
import com.project.servey.adapter.out.persistence.repository.AnswerRepository;
import com.project.servey.application.port.out.answer.CreateAnswerPort;
import com.project.servey.domain.Answer;
import com.project.servey.mapper.AnswerMapper;
import com.project.servey.util.custom.PersistenceAdapter;

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
