package com.project.survey.application.service.survey;

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

import com.project.survey.adapter.in.web.dto.response.survey.SurveyResponseDto;
import com.project.survey.adapter.out.persistence.entity.constant.SurveyType;
import com.project.survey.application.command.survey.FindSurveyCommand;
import com.project.survey.application.port.out.survey.FindSurveyPort;
import com.project.survey.application.service.survey.FindSurveyService;
import com.project.survey.domain.Survey;
import com.project.survey.exception.ErrorCode;
import com.project.survey.exception.SurveyException;
import com.project.survey.mapper.SurveyMapper;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
@DisplayName("[단위] 설문조사 서비스 테스트 - find")
public class FindSurveyServiceTest {
    @Mock
    private FindSurveyPort findSurveyPort;
    @Mock
    private SurveyMapper surveyMapper;

    @InjectMocks
    private FindSurveyService service;

    // @Test
    // @DisplayName("[happy] 유효한 id로 설문조사 게시글 1개 조회")
    // void testFindSurveySuccess() {
    //     /*
    //         public SurveyResponseDto findSurvey(FindSurveyCommand findCommand) {
    //             Survey findSurvey = findSurveyPort.findSurveyById(findCommand.getSurveyId());
    //             return surveyMapper.domainToResponseDto(findSurvey);
    //         }
    //      */

    //     //given - 필요한 객체 설정
    //     Long savedSurveyId = 2L; //가정하는 저장된 id
    //     Survey survey = getTestDomain();
    //     SurveyResponseDto surveyResponseDto = getTestDto();
    //     FindSurveyCommand command = FindSurveyCommand.of(savedSurveyId);

    //     when(surveyMapper.domainToResponseDto(survey)).thenReturn(surveyResponseDto);
    //     when(findSurveyPort.findSurveyById(command.getSurveyId())).thenReturn(survey);

    //     //when - 실제 테스트 대상 메서드 호출
    //     SurveyResponseDto result = service.findSurvey(command);

    //     //then - 결과 검증
    //     verify(findSurveyPort).findSurveyById(savedSurveyId);
    //     assertEquals(surveyResponseDto, result); // 기대값과 실제 반환값 비교
    // }

    @Test
    @DisplayName("[bad] 잘못된 surveyId로 조회하면 에러 발생시킨다.")
    void testFindSurveyFail(){
        //given
        Long savedSurveyId = 999L; 
        when(findSurveyPort.checkIsSurveyExist(savedSurveyId)).thenReturn(false);

        //when & then
        assertThatThrownBy(() -> service.checkIsSurveyExist(savedSurveyId))
        .isInstanceOf(SurveyException.class)
        .hasMessageContaining("Survey not found")
        .hasFieldOrPropertyWithValue("errorCode", ErrorCode.Survey_NOT_FOUND);
    }

    @Test
    void testFindSurveyAllList() {

    }

    @Test
    void testFindSurveyFilteredList() {

    }
    private Survey getTestDomain(){
        LocalDateTime data = LocalDateTime.of(2024,10,10,13,0,0,0);
        return Survey.of(1L, 1L, "title1", SurveyType.OX, 10, 10,data,data);
    }
    private SurveyResponseDto getTestDto(){
        LocalDateTime data = LocalDateTime.of(2024,10,10,13,0,0,0);
        return SurveyResponseDto.of(1L, 1L, "title1", SurveyType.OX, 10, 10, data,data);
    }
}
