package com.project.servey.application.service.servey;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import com.project.servey.adapter.in.web.dto.response.servey.ServeyResponseDto;
import com.project.servey.adapter.out.persistence.entity.constant.ServeyType;
import com.project.servey.application.command.servey.FindServeyCommand;
import com.project.servey.application.port.out.servey.FindServeyPort;
import com.project.servey.domain.Servey;
import com.project.servey.exception.ErrorCode;
import com.project.servey.exception.ServeyException;
import com.project.servey.mapper.ServeyMapper;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
@DisplayName("[단위] 설문조사 서비스 테스트 - find")
public class FindServeyServiceTest {
    @Mock
    private FindServeyPort findServeyPort;
    @Mock
    private ServeyMapper serveyMapper;

    @InjectMocks
    private FindServeyService service;

    @Test
    @DisplayName("[happy] 유효한 id로 설문조사 게시글 1개 조회")
    void testFindServeySuccess() {
        /*
            public ServeyResponseDto findServey(FindServeyCommand findCommand) {
                Servey findServey = findServeyPort.findServeyById(findCommand.getServeyId());
                return serveyMapper.domainToResponseDto(findServey);
            }
         */

        //given - 필요한 객체 설정
        Long savedServeyId = 1L; //가정하는 저장된 id
        Servey servey = getTestDomain();
        ServeyResponseDto serveyResponseDto = getTestDto();
        FindServeyCommand command = FindServeyCommand.of(savedServeyId);

        when(serveyMapper.domainToResponseDto(servey)).thenReturn(serveyResponseDto);
        when(findServeyPort.findServeyById(command.getServeyId())).thenReturn(servey);

        //when - 실제 테스트 대상 메서드 호출
        ServeyResponseDto result = service.findServey(command);

        //then - 결과 검증
        verify(findServeyPort).findServeyById(savedServeyId);
        assertEquals(serveyResponseDto, result); // 기대값과 실제 반환값 비교
    }

    @Test
    @DisplayName("[bad] 잘못된 serveyId로 조회하면 에러 발생시킨다.")
    void testFindServeyFail(){
        //given
        Long savedServeyId = 999L; 
        when(findServeyPort.checkIsServeyExist(savedServeyId)).thenReturn(false);

        //when & then
        assertThatThrownBy(() -> service.checkIsServeyExist(savedServeyId))
        .isInstanceOf(ServeyException.class)
        .hasMessageContaining("Servey not found")
        .hasFieldOrPropertyWithValue("errorCode", ErrorCode.SERVEY_NOT_FOUND);
    }

    @Test
    void testFindServeyAllList() {

    }

    @Test
    void testFindServeyFilteredList() {

    }
    private Servey getTestDomain(){
        LocalDateTime data = LocalDateTime.of(2024,10,10,13,0,0,0);
        return Servey.of(1L, 1L, "title1", ServeyType.OX, 10, 10,data,data);
    }
    private ServeyResponseDto getTestDto(){
        LocalDateTime data = LocalDateTime.of(2024,10,10,13,0,0,0);
        return ServeyResponseDto.of(1L, 1L, "title1", ServeyType.OX, 10, 10, data,data);
    }
}
