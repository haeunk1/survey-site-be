package com.project.servey.application.service.servey;

import com.project.servey.application.port.in.servey.DeleteServeyUseCase;
import com.project.servey.application.port.out.servey.DeleteServeyPort;
import com.project.servey.application.port.out.servey.FindServeyPort;
import com.project.servey.exception.ErrorCode;
import com.project.servey.exception.ServeyException;
import com.project.servey.util.custom.UseCase;

import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = false)
@UseCase
public class DeleteServeyService implements DeleteServeyUseCase{

    private final DeleteServeyPort deleteServeyPort;
    private final FindServeyService findService;

    @Override
    public Long deleteServeyById(Long serveyId) {
        //삭제된 게시글이 아닌지 검증
        findService.checkIsServeyExist(serveyId);
        return deleteServeyPort.deleteServey(serveyId);
    }
    
}
