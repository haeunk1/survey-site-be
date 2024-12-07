package com.project.survey.adapter.in.web.controller;

import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.survey.adapter.in.web.controller.SurveyController;
import com.project.survey.adapter.in.web.dto.response.survey.SurveyResponseDto;
import com.project.survey.adapter.out.persistence.entity.constant.SurveyType;
import com.project.survey.application.command.survey.CreateSurveyCommand;
import com.project.survey.application.command.survey.FindSurveyCommand;
import com.project.survey.application.port.in.survey.CreateSurveyUseCase;
import com.project.survey.application.port.in.survey.DeleteSurveyUseCase;
import com.project.survey.application.port.in.survey.FindSurveyUseCase;
import com.project.survey.application.port.in.survey.UpdateSurveyUseCase;

import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.when;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("설문조사 컨트롤러 테스트")
@AutoConfigureMockMvc(addFilters = false) // Security 필터 비활성화
@WebMvcTest(SurveyController.class) //슬라이스테스트, 단위테스트와 통합테스트의 중간개념.(하나의 레이어 기준으로 테스트 진행)
public class SurveyControllerTest {

    //컨트롤러의 api테스트를 위해 사용(서블릿 컨테이너의 구동없이 가상의 mvc환경에서 모의 httml서블릿을 요청하는 유틸리티 클래스)
    @Autowired MockMvc mockMvc;
    @MockBean FindSurveyUseCase findSurveyUseCase; //mock객체를 주입, 
    @MockBean CreateSurveyUseCase createSurveyUseCase;
    @MockBean DeleteSurveyUseCase deleteSurveyUseCase;
    @MockBean UpdateSurveyUseCase updateSurveyUseCase;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testFindSurvey() throws Exception{
        SurveyResponseDto surveyResponseDto = getTestDto();

        //given
        //테스트 과정에서 맡을 동작을 정의
        given(findSurveyUseCase.findSurvey(any(FindSurveyCommand.class)))
        .willReturn(surveyResponseDto);

        //when&then
        MvcResult result = mockMvc.perform(
            get("/survey/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.title").exists())
            .andExpect(jsonPath("$.memberId").exists())
            .andDo(print())
            .andReturn();

            System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    void testCreateSurvey() throws Exception{
        //Mock 객체에서 특정 메서드가 실행되는 경우 실제 Return을 줄 수 없기 때문에 가정 사항을 만들어줌

        SurveyResponseDto surveyResponseDto = getTestDto();

        given(createSurveyUseCase.createSurvey(any(CreateSurveyCommand.class)))
        .willReturn(surveyResponseDto);

        //json 요청 바디 설정
        String requestBody = objectMapper.writeValueAsString(surveyResponseDto);
        
        MvcResult result = mockMvc.perform(
            post("/survey/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").exists())
                .andExpect(jsonPath("$.memberId").exists())
                .andDo(print())
                .andReturn();
        
                System.out.println(result.getResponse().getContentAsString());

    }

    @Test
    void testDeleteSurvey() {
    }


    @Test
    void testFindSurveyAllList() {

    }

    @Test
    void testFindSurveyFilteredList() {

    }

    @Test
    void testUpdateSurvey() {

    }

    private SurveyResponseDto getTestDto(){
        return SurveyResponseDto.of(1L, 1L, "title1", SurveyType.OX, 10, 10, LocalDateTime.now(), LocalDateTime.now());
    }
}
