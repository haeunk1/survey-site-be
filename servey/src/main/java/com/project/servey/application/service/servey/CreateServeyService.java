package com.project.servey.application.service.servey;

import com.project.servey.adapter.in.web.dto.response.servey.ServeyResponseDto;
import com.project.servey.application.command.servey.CreateServeyCommand;
import com.project.servey.application.port.in.servey.CreateServeyUseCase;
import com.project.servey.application.port.out.servey.CreateServeyPort;
import com.project.servey.domain.Servey;
import com.project.servey.mapper.ServeyMapper;
import com.project.servey.util.custom.UseCase;

import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;


@UseCase
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CreateServeyService implements CreateServeyUseCase{
    private final CreateServeyPort createServeyPort;
    private final ServeyMapper serveyMapper;

@Transactional
@Override
public ServeyResponseDto createServey(CreateServeyCommand command) {
    /**
     * @param command 설문조사 생성 요청
     * @return 설문조사 생성 결과
     * @apiNote 설문조사 생성
     */
    Servey servey = serveyMapper.commandToDomain(command);
    Servey createdServey = createServeyPort.createServey(servey);

    return serveyMapper.domainToResponseDto(createdServey);
}    
    
}
