package com.project.survey.adapter.out.persistence.adapter;

import java.util.List;

import com.project.survey.adapter.out.persistence.entity.survey.QuestionEntity;
import com.project.survey.adapter.out.persistence.repository.QuestionRepository;
import com.project.survey.application.port.out.question.CreateQuestionPort;
import com.project.survey.application.port.out.question.FindQuestionPort;
import com.project.survey.domain.Question;
import com.project.survey.mapper.QuestionMapper;
import com.project.survey.util.custom.PersistenceAdapter;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@PersistenceAdapter
public class QuestionPersistenceAdapter implements CreateQuestionPort, FindQuestionPort{
    private final QuestionRepository repository;
    private final QuestionMapper mapper;

    @Override
    public List<Question> createQuestion(List<Question> list) {
        //domain to entity
        List<QuestionEntity> entityList = mapper.domainsToEntities(list);
        List<QuestionEntity> rtnEntityList =repository.saveAll(entityList);
        return mapper.entitiesToDomains(rtnEntityList);
    }

    @Override
    public List<Question> findQuestionList(Long surveyId) {
        List<QuestionEntity> rtnEntityList = repository.findBySurveyId(surveyId);
        return mapper.entitiesToDomains(rtnEntityList);
    }
    
}
