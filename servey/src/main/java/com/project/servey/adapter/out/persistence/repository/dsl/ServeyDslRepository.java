package com.project.servey.adapter.out.persistence.repository.dsl;

import org.springframework.stereotype.Repository;

import com.project.servey.adapter.in.web.dto.request.servey.ServeyListRequestDto;
import com.project.servey.adapter.in.web.dto.response.servey.ServeyListResponseDto;
import com.project.servey.adapter.out.persistence.entity.constant.ListOrderType;
import com.project.servey.adapter.out.persistence.entity.servey.ServeyEntity;
import com.project.servey.application.command.servey.FindServeyListCommand;
import com.project.servey.domain.Servey;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import static com.project.servey.adapter.out.persistence.entity.servey.QServeyEntity.serveyEntity;
import static com.project.servey.adapter.out.persistence.entity.member.QMemberEntity.memberEntity;
import static com.project.servey.adapter.out.persistence.entity.servey.QQuestionEntity.questionEntity;


import java.time.LocalDateTime;
import java.util.List;;
@RequiredArgsConstructor
@Repository
public class ServeyDslRepository {
    private final JPAQueryFactory queryFactory;

    /*
     * [DELETE] serveyId에 해당하는 설문조사 삭제처리(delete_yn = 'Y')한다.
     */
    public Long deleteServey(Long serveyId){
        return queryFactory.update(serveyEntity)
        .set(serveyEntity.deleteYn,"Y")
        .set(serveyEntity.updatedAt,LocalDateTime.now())
        .where(serveyEntity.serveyId.eq(serveyId))
        .execute();
    }

    /*
     * [Update] serveyId에 해당하는 설문조사 수정
     */
    public Long updateServey(Servey servey){
        return queryFactory.update(serveyEntity)
        .set(serveyEntity.title,servey.getTitle())
        .set(serveyEntity.type,servey.getType())
        .set(serveyEntity.perPoint,servey.getPerPoint())
        .set(serveyEntity.limitSubmit,servey.getLimitSubmit())
        .set(serveyEntity.startdate,servey.getStartdate())
        .set(serveyEntity.enddate,servey.getEnddate())
        .set(serveyEntity.updatedAt,LocalDateTime.now())
        .where(serveyEntity.serveyId.eq(servey.getServeyId()))
        .execute();
    }
   
    /*
     * SELECT A.title,A.TYPE,A.per_point,A.limit_submit,A.startdate,A.enddate ,B.name ,C.CNT
        FROM servey.servey AS A 
        INNER JOIN servey.member AS B ON a.member_id = b.member_id
        INNER JOIN (SELECT servey_id,COUNT(QUESTION_ID) AS CNT FROM SERVEY.QUESTION) AS C ON A.servey_id = c.servey_id
        #INNER JOIN (SELECT question_id FROM serbey.answer) AS D ON d.
        WHERE A.delete_yn = 'N' AND A.TITLE LIKE '%%'
        ORDER BY A.per_point;
     * 
     */
    public List<ServeyListResponseDto> selectServeyFilteredList(FindServeyListCommand command){
        
        return queryFactory
            .select(Projections.constructor(ServeyListResponseDto.class,//프로젝션 매핑, 결과를 ServeyListResponseDto로 매핑
                serveyEntity.title,
                serveyEntity.type,
                serveyEntity.perPoint,
                serveyEntity.limitSubmit,
                serveyEntity.startdate,
                serveyEntity.enddate,
                memberEntity.name,
                // 서브쿼리로 question 수 카운트
                JPAExpressions.select(questionEntity.count())
                              .from(questionEntity)
                              .where(questionEntity.serveyId.eq(serveyEntity.serveyId))
            ))
            .from(serveyEntity)
            .join(memberEntity).on(serveyEntity.memberId.eq(memberEntity.memberId))
            .where(
                serveyEntity.deleteYn.eq("N"),
                serveyEntity.title.containsIgnoreCase(command.getTitle())
            )
            .orderBy(serveyEntity.perPoint.asc())
            .orderBy(command.getOrderType() == ListOrderType.POINT_DESC ? serveyEntity.perPoint.desc() : 
                command.getOrderType() == ListOrderType.POINT_ASC ? serveyEntity.perPoint.asc() :
                command.getOrderType() == ListOrderType.ENDDATE_DESC ? serveyEntity.enddate.desc() : serveyEntity.enddate.asc() 
            )
            .fetch();
    }
}
