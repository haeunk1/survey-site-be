package com.project.servey.adapter.out.persistence.adapter;

import java.util.List;

import com.project.servey.adapter.out.persistence.entity.servey.QuestionEntity;
import com.project.servey.adapter.out.persistence.repository.QustionRepository;
import com.project.servey.application.port.out.question.CreateQuestionPort;
import com.project.servey.domain.Question;
import com.project.servey.mapper.QuestionMapper;
import com.project.servey.util.custom.PersistenceAdapter;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@PersistenceAdapter
public class QuestionPersistenceAdapter implements CreateQuestionPort{
    private final QustionRepository repository;
    private final QuestionMapper mapper;

    @Override
    public List<Question> createQuestion(List<Question> list) {
        //domain to entity
        List<QuestionEntity> entityList = mapper.domainsToEntities(list);
        List<QuestionEntity> rtn = repository.saveAll(entityList);
        //entity to domain
        return mapper.entitiesToDomains(rtn);
    }
    
}
