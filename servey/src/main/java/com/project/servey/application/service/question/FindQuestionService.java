package com.project.servey.application.service.question;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.project.servey.adapter.in.web.dto.response.QuestionResponseDto;
import com.project.servey.application.port.in.question.FindQuestionUseCase;
import com.project.servey.application.port.out.question.FindQuestionPort;
import com.project.servey.domain.Question;
import com.project.servey.mapper.QuestionMapper;
import com.project.servey.util.custom.UseCase;

import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
@Transactional(readOnly = false)
public class FindQuestionService implements FindQuestionUseCase{

    private final FindQuestionPort findQuestionPort;
    private final QuestionMapper mapper;

    @Override
    @Transactional
    public List<QuestionResponseDto> findQuestionList(Long serveyId) {
        List<Question> rtnDomainList = findQuestionPort.findQuestionList(serveyId);
        return mapper.domainToResponseDto(rtnDomainList);
    }
    
}
