package com.project.survey.application.service.answer;

import org.springframework.transaction.annotation.Transactional;

import com.project.survey.application.command.anwer.CreateAnswerCommand;
import com.project.survey.application.port.in.answer.CreateAnswerUseCase;
import com.project.survey.application.port.out.answer.CreateAnswerPort;
import com.project.survey.mapper.AnswerMapper;
import com.project.survey.util.custom.UseCase;

import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
@Transactional(readOnly = false)
public class CreateAnswerService implements CreateAnswerUseCase{
    //private final CreateAnswerPort createAnswerPort;
    //private final AnswerMapper mapper;

    @Override
    public int createAnswer(CreateAnswerCommand command) {
        //command (memberId,surveyId,answers)
        //1.memberId 유효성 검사(존재여부,제출여부)..제출여부는 페이지 입장시 확인 필요
        //2.surveyId 유효성 검사(존재여부,삭제여부)
        //3.answer 각 답변에 대한 유효성검사(문항검증 : questionRepository.existsBySurveyIdAndQuestionId)
        //4.동시성

    //         // 답변 저장
    //         AnswerEntity answerEntity = new AnswerEntity(
    //             request.getSurveyId(),
    //             answer.getQuestionId(),
    //             answer.getAnswer()
    //         );
    //         answerRepository.save(answerEntity);
        return 0;
    }
    
}
