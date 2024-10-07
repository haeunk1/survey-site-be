package com.project.servey.application.service.servey;

import org.springframework.transaction.annotation.Transactional;

import com.project.servey.adapter.in.web.dto.response.servey.ServeyResponseDto;
import com.project.servey.application.command.servey.FindServeyCommand;
import com.project.servey.application.port.in.servey.FindServeyUseCase;
import com.project.servey.domain.Servey;
import com.project.servey.util.custom.UseCase;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@UseCase
public class FindServeyService implements FindServeyUseCase{
    //private final FindServeyPort findServeyPort;

    @Override
    public ServeyResponseDto findServey(FindServeyCommand findCommand) {
        //Servey findServey = findServeyPort.
        throw new UnsupportedOperationException("Unimplemented method 'createServey'");
    }
    
}
