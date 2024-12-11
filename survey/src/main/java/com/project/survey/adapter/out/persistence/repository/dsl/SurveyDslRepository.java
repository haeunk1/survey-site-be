package com.project.survey.adapter.out.persistence.repository.dsl;

import org.springframework.stereotype.Repository;

import com.project.survey.adapter.in.web.dto.request.survey.SurveyListRequestDto;
import com.project.survey.adapter.in.web.dto.response.survey.SurveyListResponseDto;
import com.project.survey.adapter.out.persistence.entity.constant.ListOrderType;
import com.project.survey.adapter.out.persistence.entity.survey.SurveyEntity;
import com.project.survey.application.command.survey.FindSurveyListCommand;
import com.project.survey.domain.Survey;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import static com.project.survey.adapter.out.persistence.entity.survey.QSurveyEntity.surveyEntity;
import static com.project.survey.adapter.out.persistence.entity.member.QMemberEntity.memberEntity;
import static com.project.survey.adapter.out.persistence.entity.survey.QQuestionEntity.questionEntity;


import java.time.LocalDateTime;
import java.util.List;;
@RequiredArgsConstructor
@Repository
public class SurveyDslRepository {
    private final JPAQueryFactory queryFactory;

    /*
     * [DELETE] surveyId에 해당하는 설문조사 삭제처리(delete_yn = 'Y')한다.
     */
    public Long deleteSurvey(Long surveyId){
        return queryFactory.update(surveyEntity)
        .set(surveyEntity.deleteYn,"Y")
        .set(surveyEntity.updatedAt,LocalDateTime.now())
        .where(surveyEntity.surveyId.eq(surveyId))
        .execute();
    }

    /*
     * [Update] surveyId에 해당하는 설문조사 수정
     */
    public Long updateSurvey(Survey survey){
        return queryFactory.update(surveyEntity)
        .set(surveyEntity.title,survey.getTitle())
        .set(surveyEntity.description,survey.getDescription())
        .set(surveyEntity.type,survey.getType())
        .set(surveyEntity.perPoint,survey.getPerPoint())
        .set(surveyEntity.limitSubmit,survey.getLimitSubmit())
        .set(surveyEntity.startdate,survey.getStartdate())
        .set(surveyEntity.enddate,survey.getEnddate())
        .set(surveyEntity.updatedAt,LocalDateTime.now())
        .where(surveyEntity.surveyId.eq(survey.getSurveyId()))
        .execute();
    }
   
    /*
     * SELECT A.title,A.TYPE,A.per_point,A.limit_submit,A.startdate,A.enddate ,B.name,
        (SELECT COUNT(C.question_id) FROM survey.question AS C WHERE C.survey_id = A.survey_id) AS CNT
        FROM survey.survey AS A 
        INNER JOIN survey.member AS B ON a.member_id = b.member_id
        WHERE A.delete_yn = 'N' AND A.TITLE LIKE '%testTitle%'
        ORDER BY A.per_point desc;
     * 
     */
    public List<SurveyListResponseDto> selectSurveyFilteredList(FindSurveyListCommand command){
        
        return queryFactory
            .select(Projections.constructor(SurveyListResponseDto.class,//프로젝션 매핑, 결과를 SurveyListResponseDto로 매핑
                surveyEntity.surveyId,    
                memberEntity.name,    
                surveyEntity.title,
                surveyEntity.description,
                surveyEntity.type,
                surveyEntity.perPoint,
                surveyEntity.limitSubmit,
                surveyEntity.startdate,
                surveyEntity.enddate,
                // 서브쿼리로 question 수 카운트
                JPAExpressions.select(questionEntity.count())
                              .from(questionEntity)
                              .where(questionEntity.surveyId.eq(surveyEntity.surveyId))
            ))
            .from(surveyEntity)
            .join(memberEntity).on(surveyEntity.memberId.eq(memberEntity.memberId))
            .where(
                surveyEntity.deleteYn.eq("N"),
                surveyEntity.title.containsIgnoreCase(command.getTitle())
            )
            .orderBy(command.getOrderType() == ListOrderType.POINT_DESC ? surveyEntity.perPoint.desc() : 
                command.getOrderType() == ListOrderType.POINT_ASC ? surveyEntity.perPoint.asc() :
                command.getOrderType() == ListOrderType.ENDDATE_DESC ? surveyEntity.enddate.desc() : surveyEntity.enddate.asc() 
            )
            .fetch();
    }
}
