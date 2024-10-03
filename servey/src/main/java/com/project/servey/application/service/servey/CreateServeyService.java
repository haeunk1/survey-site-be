package com.project.servey.application.service.servey;

import com.project.servey.adapter.in.web.dto.response.servey.ServeyResponseDto;
import com.project.servey.application.command.servey.CreateServeyCommand;
import com.project.servey.application.port.in.servey.CreateServeyUseCase;

import jakarta.transaction.TransactionScoped;
import jakarta.transaction.Transactional;

public class CreateServeyService implements CreateServeyUseCase{
@Transactional
@Override
public ServeyResponseDto createServey(CreateServeyCommand command) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'createServey'");
}    
    
}
