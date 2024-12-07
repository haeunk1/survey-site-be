package com.project.survey.application.service.question;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.project.survey.adapter.in.web.dto.response.question.QuestionResponseDto;
import com.project.survey.application.port.in.question.FindQuestionUseCase;
import com.project.survey.application.port.out.question.FindQuestionPort;
import com.project.survey.domain.Question;
import com.project.survey.mapper.QuestionMapper;
import com.project.survey.util.custom.UseCase;

import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
@Transactional(readOnly = false)
public class FindQuestionService implements FindQuestionUseCase{

    private final FindQuestionPort findQuestionPort;
    private final QuestionMapper mapper;

    @Override
    @Transactional
    public List<QuestionResponseDto> findQuestionList(Long surveyId) {
        List<Question> rtnDomainList = findQuestionPort.findQuestionList(surveyId);
        return mapper.domainToResponseDto(rtnDomainList);
    }
    
}
