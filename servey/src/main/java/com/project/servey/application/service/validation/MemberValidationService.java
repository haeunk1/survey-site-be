package com.project.servey.application.service.validation;

import com.project.servey.adapter.out.persistence.repository.AnswerRepository;
import com.project.servey.adapter.out.persistence.repository.MemberRepository;

import com.project.servey.util.custom.UseCase;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = false)
@UseCase
public class MemberValidationService {
    // private final MemberRepository memberRepository;
    // private final AnswerRepository answerRepository;

    // public void validateMember(Long memberId, Long serveyId) {
    //     if (!memberRepository.existsById(memberId)) {
    //         throw new IllegalArgumentException("유효하지 않은 사용자입니다.");
    //     }
    //     if (answerRepository.existsByMemberIdAndServeyId(memberId, serveyId)) {
    //         throw new IllegalStateException("이미 응답한 설문입니다.");
    //     }
    //}
}
