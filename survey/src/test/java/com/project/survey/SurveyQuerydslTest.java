package com.project.survey;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import com.project.survey.adapter.in.web.dto.request.survey.SurveyListRequestDto;
import com.project.survey.adapter.in.web.dto.response.survey.SurveyListResponseDto;
import com.project.survey.adapter.out.persistence.entity.constant.ListOrderType;
import com.project.survey.adapter.out.persistence.entity.constant.SurveyType;
import com.project.survey.adapter.out.persistence.repository.MemberRepository;
import com.project.survey.adapter.out.persistence.repository.QuestionRepository;
import com.project.survey.adapter.out.persistence.repository.SurveyRepository;
import com.project.survey.application.command.survey.FindSurveyListCommand;
import com.project.survey.application.service.survey.FindSurveyService;
import com.project.survey.config.jpa.QuerydslConfig;
import com.project.survey.domain.Member;
import com.project.survey.domain.Question;
import com.project.survey.domain.Survey;
import com.project.survey.mapper.MemberMapper;
import com.project.survey.mapper.QuestionMapper;
import com.project.survey.mapper.SurveyMapper;
import java.util.List;
import java.time.LocalDateTime;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class SurveyQuerydslTest {

    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private SurveyMapper surveyMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private FindSurveyService surveyService; // QueryDSL 쿼리 메서드가 포함된 서비스


    @BeforeEach
    public void setup() {
        //member
        Long memberId = 1L;
        Member newMember = Member.of(memberId,"test@example.com", "Test User", "password");
        memberRepository.save(memberMapper.domainToEntity(newMember));

        //survey
        Long surveyId = 1L;
        Survey newSurvey = Survey.of(surveyId,memberId,"test Title","test description", SurveyType.OX, 10, 100,10 * 100,LocalDateTime.now(),LocalDateTime.now());
        surveyRepository.save(surveyMapper.domainToEntity(newSurvey));
        
        Question newQuestion1 = Question.of(1L,surveyId, "Sample Question 1");
        Question newQuestion2 = Question.of(2L,surveyId, "Sample Question 2");

        // 질문 데이터 추가
        questionRepository.save(questionMapper.domaintoEntity(newQuestion1));
        questionRepository.save(questionMapper.domaintoEntity(newQuestion2));
    }

    // @Test
    // public void testSelectSurveyFilteredList() {
    //     SurveyListRequestDto dto = SurveyListRequestDto.of("tset", ListOrderType.POINT_DESC);
    //     FindSurveyListCommand command = FindSurveyListCommand.of(dto);

    //     List<SurveyListResponseDto> result = surveyService.findSurveyFilteredList(command);

    //     // 검증
    //     assertThat(result).isNotEmpty();
    //     assertThat(result.get(0).getTitle()).isEqualTo("Test Title");
    //     //assertThat(result.get(0).getName()).isEqualTo("Test User");
    //     //assertThat(result.get(0).getQuestionCnt()).isEqualTo(2L); // questionCnt가 서브쿼리 카운트와 일치하는지 확인
    // }

    // @Test
    // public void tmpTest(){
    //     System.out.println("test");
    // }
}