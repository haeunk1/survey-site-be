package com.project.servey;

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

import com.project.servey.adapter.in.web.dto.request.servey.ServeyListRequestDto;
import com.project.servey.adapter.in.web.dto.response.servey.ServeyListResponseDto;
import com.project.servey.adapter.out.persistence.entity.constant.ListOrderType;
import com.project.servey.adapter.out.persistence.entity.constant.ServeyType;
import com.project.servey.adapter.out.persistence.repository.MemberRepository;
import com.project.servey.adapter.out.persistence.repository.QuestionRepository;
import com.project.servey.adapter.out.persistence.repository.ServeyRepository;
import com.project.servey.application.command.servey.FindServeyListCommand;
import com.project.servey.application.service.servey.FindServeyService;
import com.project.servey.config.jpa.QuerydslConfig;
import com.project.servey.domain.Member;
import com.project.servey.domain.Question;
import com.project.servey.domain.Servey;
import com.project.servey.mapper.MemberMapper;
import com.project.servey.mapper.QuestionMapper;
import com.project.servey.mapper.ServeyMapper;
import java.util.List;
import java.time.LocalDateTime;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class ServeyQuerydslTest {

    @Autowired
    private ServeyRepository serveyRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private ServeyMapper serveyMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private FindServeyService serveyService; // QueryDSL 쿼리 메서드가 포함된 서비스


    @BeforeEach
    public void setup() {
        //member
        Long memberId = 1L;
        Member newMember = Member.of(memberId,"test@example.com", "Test User", "password");
        memberRepository.save(memberMapper.domainToEntity(newMember));

        //servey
        Long serveyId = 1L;
        Servey newServey = Servey.of(serveyId,memberId,"test Title", ServeyType.OX, 10, 100,LocalDateTime.now(),LocalDateTime.now());
        serveyRepository.save(serveyMapper.domainToEntity(newServey));
        
        Question newQuestion1 = Question.of(1L,serveyId, "Sample Question 1");
        Question newQuestion2 = Question.of(2L,serveyId, "Sample Question 2");

        // 질문 데이터 추가
        questionRepository.save(questionMapper.domaintoEntity(newQuestion1));
        questionRepository.save(questionMapper.domaintoEntity(newQuestion2));
    }

    @Test
    public void testSelectServeyFilteredList() {
        ServeyListRequestDto dto = ServeyListRequestDto.of("tset", ListOrderType.POINT_DESC);
        FindServeyListCommand command = FindServeyListCommand.of(dto);

        List<ServeyListResponseDto> result = serveyService.findServeyFilteredList(command);

        // 검증
        assertThat(result).isNotEmpty();
        assertThat(result.get(0).getTitle()).isEqualTo("Test Title");
        //assertThat(result.get(0).getName()).isEqualTo("Test User");
        //assertThat(result.get(0).getQuestionCnt()).isEqualTo(2L); // questionCnt가 서브쿼리 카운트와 일치하는지 확인
    }

    @Test
    public void tmpTest(){
        System.out.println("test");
    }
}