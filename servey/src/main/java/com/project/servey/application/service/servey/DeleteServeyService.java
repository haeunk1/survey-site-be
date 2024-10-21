package com.project.servey.application.service.servey;

import com.project.servey.application.port.in.servey.DeleteServeyUseCase;
import com.project.servey.application.port.out.servey.DeleteServeyPort;
import com.project.servey.util.custom.UseCase;

import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = false)
@UseCase
public class DeleteServeyService implements DeleteServeyUseCase{

    private final DeleteServeyPort deleteServeyPort;

    @Override
    public Long deleteServeyById(Long serveyId) {
        return deleteServeyPort.deleteServey(serveyId);
    }
    
}
