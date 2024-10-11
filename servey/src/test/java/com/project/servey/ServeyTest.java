package com.project.servey;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.servey.adapter.in.web.dto.response.servey.ServeyResponseDto;
import com.project.servey.application.command.servey.CreateServeyCommand;
import com.project.servey.application.port.in.servey.CreateServeyUseCase;
import com.project.servey.application.port.out.servey.CreateServeyPort;
import com.project.servey.application.port.out.servey.FindServeyPort;
import com.project.servey.application.service.servey.CreateServeyService;
import com.project.servey.domain.Servey;
import com.project.servey.mapper.ServeyMapper;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class ServeyTest {
    /*
        .도메인 엔터티를 구현할 때는 단위 테스트로 커버한다.
        .유스케이스를 구현할 때는 단위 테스트로 커버한다.
        .어댑터를 구현할 때는 통합 테스트로 커버한다.
        .사용자가 취할 수 있는 애플리케이션 경로는 시스템 테스트로 커버한다. 
     */

    @Mock
    private CreateServeyPort createServeyPort;

    @Mock
    private FindServeyPort findServeyPort;

    @Mock
    private ServeyMapper serveyMapper;

    @InjectMocks
    private CreateServeyService service;

    @Test
    @DisplayName("설문조사 생성 테스트")
    void createServeyTest(){
        Long serveyid = 1L;

        Servey servey = Servey.builder()
            .userId("test1")
            .title("title1")
            .category(1)
            .limitSubmit(1)
            .perPoint(10)
            .startdate(LocalDateTime.of(2024, 10, 10, 0, 0))
            .enddate(LocalDateTime.of(2024, 10, 10, 23,59))
            .build();

        ServeyResponseDto responseDto = serveyMapper.domainToResponseDto(servey);
        CreateServeyCommand command = serveyMapper.domainToConnCommand(servey);

        //given
        given(createServeyPort.createServey(any(Servey.class))).willReturn(servey);
        given(serveyMapper.domainToResponseDto(any(Servey.class))).willReturn(responseDto);
        //when
        ServeyResponseDto result = service.createServey(command);

        //then
        assertThat(result.getServeyId()).isEqualTo(serveyid);

    }

}
