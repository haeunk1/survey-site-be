package com.project.servey.adapter.out.persistence.repository.dsl;

import org.springframework.stereotype.Repository;

import com.project.servey.adapter.in.web.dto.request.servey.ServeyListRequestDto;
import com.project.servey.adapter.out.persistence.entity.servey.ServeyEntity;
import com.project.servey.application.command.servey.FindServeyListCommand;
import com.project.servey.domain.Servey;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import static com.project.servey.adapter.out.persistence.entity.servey.QServeyEntity.serveyEntity;

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
   
    public List<ServeyListResponseDto> selectServeyFilteredList(FindServeyListCommand command){
        //return queryFactory.select()
    }
}
