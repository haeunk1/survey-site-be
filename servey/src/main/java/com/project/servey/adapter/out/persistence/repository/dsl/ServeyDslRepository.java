package com.project.servey.adapter.out.persistence.repository.dsl;

import org.springframework.stereotype.Repository;

import com.project.servey.adapter.out.persistence.entity.servey.ServeyEntity;
import com.project.servey.domain.Servey;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class ServeyDslRepository {
    private final JPAQueryFactory queryFactory;

    /*
     * [DELETE] serveyId에 해당하는 댓글을 삭제처리(delete_yn = 'Y')한다.
     */
    public Long deleteServey(Long serveyId){
        
        //queryFactory.update(ServeyEntity)
        return 1L;
    }

   
    // public Long softDeleteComment(Long commentId) {
    //     return queryFactory
    //             .update(commentEntity)
    //             .set(commentEntity.delYn, "Y")
    //             .set(commentEntity.updateDateTime, LocalDateTime.now())
    //             .where(commentEntity.id.eq(commentId))
    //             .execute();
    // }

}
