package com.project.servey.adapter.out.persistence.repository.dsl;

import org.springframework.stereotype.Repository;

import com.project.servey.adapter.out.persistence.entity.servey.ServeyEntity;
import com.project.servey.domain.Servey;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import static com.project.servey.adapter.out.persistence.entity.servey.QServeyEntity.serveyEntity;

import java.time.LocalDateTime;;
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
   
}
