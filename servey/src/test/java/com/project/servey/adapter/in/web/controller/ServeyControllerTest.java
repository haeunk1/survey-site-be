package com.project.servey.adapter.in.web.controller;

import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.project.servey.adapter.in.web.dto.response.servey.ServeyResponseDto;
import com.project.servey.adapter.out.persistence.entity.constant.ServeyType;
import com.project.servey.application.command.servey.FindServeyCommand;
import com.project.servey.application.port.in.servey.CreateServeyUseCase;
import com.project.servey.application.port.in.servey.DeleteServeyUseCase;
import com.project.servey.application.port.in.servey.FindServeyUseCase;
import com.project.servey.application.port.in.servey.UpdateServeyUseCase;
import com.project.servey.config.security.SecurityConfig;

import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.when;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("설문조사 컨트롤러 테스트")
@AutoConfigureMockMvc(addFilters = false) // Security 필터 비활성화
@WebMvcTest(ServeyController.class) //슬라이스테스트, 단위테스트와 통합테스트의 중간개념.(하나의 레이어 기준으로 테스트 진행)
public class ServeyControllerTest {

    //컨트롤러의 api테스트를 위해 사용(서블릿 컨테이너의 구동없이 가상의 mvc환경에서 모의 httml서블릿을 요청하는 유틸리티 클래스)
    @Autowired MockMvc mockMvc;
    @MockBean CreateServeyUseCase createServeyUseCase;
    @MockBean FindServeyUseCase findServeyUseCase; //mock객체를 주입, 
    @MockBean DeleteServeyUseCase deleteServeyUseCase;
    @MockBean UpdateServeyUseCase updateServeyUseCase;

    @Test
    void testFindServey() throws Exception{
        long serveyId = 1L;
        FindServeyCommand findServeyCommand = FindServeyCommand.of(serveyId);
        ServeyResponseDto serveyResponseDto = ServeyResponseDto.of(serveyId, 1L, "title1", ServeyType.OX, 10, 10, LocalDateTime.now(), LocalDateTime.now());

        //given
        //테스트 과정에서 맡을 동작을 정의
        given(findServeyUseCase.findServey(eq(findServeyCommand))).willReturn(serveyResponseDto);

        //when&then
        MvcResult result = mockMvc.perform(
            get("/servey/1"))
            .andExpect(status().isOk())
            //.andExpect(jsonPath("$.title").exists())
            //.andExpect(jsonPath("$.memberId").exists())
            .andDo(print())
            .andReturn();

            System.out.println(result.getResponse().getContentAsString());
       
    }

    @Test
    void testCreateServey() throws Exception{
        
    }

    @Test
    void testDeleteServey() {

    }


    @Test
    void testFindServeyAllList() {

    }

    @Test
    void testFindServeyFilteredList() {

    }

    @Test
    void testUpdateServey() {

    }
}
