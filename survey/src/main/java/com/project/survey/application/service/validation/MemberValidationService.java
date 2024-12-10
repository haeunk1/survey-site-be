package com.project.survey.application.service.validation;

import com.project.survey.adapter.out.persistence.repository.AnswerRepository;
import com.project.survey.adapter.out.persistence.repository.MemberRepository;
import com.project.survey.exception.ErrorCode;
import com.project.survey.exception.SurveyException;
import com.project.survey.util.custom.UseCase;

import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = false)
@UseCase
public class MemberValidationService {
    private final MemberRepository memberRepository;
    private final AnswerRepository answerRepository;

    public void validateMember(Long memberId) {
        if (!memberRepository.existsById(memberId)) {
            throw new SurveyException(ErrorCode.SURVEY_NOT_FOUND);
        }
    }
    public void checkAlreadySubmit(Long memberId, Long surveyId){
        if (answerRepository.existsByMemberIdAndSurveyId(memberId, surveyId)) {
            throw new SurveyException(ErrorCode.ANSWER_ALREADY_EXIST);
        }
    }
}
