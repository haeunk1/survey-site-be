package com.project.survey.application.service.validation;

import com.project.survey.adapter.out.persistence.repository.AnswerRepository;
import com.project.survey.adapter.out.persistence.repository.MemberRepository;

import com.project.survey.util.custom.UseCase;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = false)
@UseCase
public class MemberValidationService {
    // private final MemberRepository memberRepository;
    // private final AnswerRepository answerRepository;

    // public void validateMember(Long memberId, Long surveyId) {
    //     if (!memberRepository.existsById(memberId)) {
    //         throw new IllegalArgumentException("유효하지 않은 사용자입니다.");
    //     }
    //     if (answerRepository.existsByMemberIdAndSurveyId(memberId, surveyId)) {
    //         throw new IllegalStateException("이미 응답한 설문입니다.");
    //     }
    //}
}
