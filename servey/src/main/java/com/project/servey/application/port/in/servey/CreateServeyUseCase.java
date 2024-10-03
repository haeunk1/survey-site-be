package com.project.servey.application.port.in.servey;

import com.project.servey.adapter.in.web.dto.response.servey.ServeyResponseDto;
import com.project.servey.application.command.servey.CreateServeyCommand;

public interface CreateServeyUseCase {
    ServeyResponseDto createServey(CreateServeyCommand command);
} 
