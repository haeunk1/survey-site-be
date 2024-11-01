package com.project.servey.domain;

import java.time.LocalDateTime;

import com.project.servey.adapter.out.persistence.entity.constant.ListOrderType;
import com.project.servey.adapter.out.persistence.entity.constant.ServeyType;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Servey {
    private Long serveyId;
    private Long memberId;
    private String title;
    private ServeyType type;
    private int perPoint;
    private int limitSubmit;
    private LocalDateTime startdate;
    private LocalDateTime enddate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String deleteYn;

    private String name;
    private int questionCnt;
    //private int submitCnt; //추가예정

    // factory method
    public static Servey of(Long serveyId, Long memberId, String title, 
    ServeyType type, int perPoint, int limitSubmit, 
    LocalDateTime startdate, LocalDateTime enddate) {
        return Servey.builder()
                .memberId(memberId)
                .title(title)
                .type(type)
                .perPoint(perPoint)
                .limitSubmit(limitSubmit)
                .startdate(startdate)
                .enddate(enddate)
                .createdAt(LocalDateTime.now())
                .updatedAt(null)
                .deleteYn("N")
                .build();
    }
}
