package com.project.servey.application.service.validation;

import com.project.servey.adapter.out.persistence.entity.servey.ServeyEntity;
import com.project.servey.adapter.out.persistence.repository.ServeyRepository;

import com.project.servey.util.custom.UseCase;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = false)
@UseCase
public class ServeyValidationService {
    //FindserveyService - checkIsServeyExist 수정 필요
    // private final ServeyRepository serveyRepository;

    // public void validateServey(Long serveyId) {
    //     ServeyEntity servey = serveyRepository.findById(serveyId)
    //             .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 설문입니다."));
    //     if (servey.isDeleted()) {
    //         throw new IllegalStateException("삭제된 설문입니다.");
    //     }
    //}
}
