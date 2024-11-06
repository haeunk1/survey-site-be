package com.project.servey.adapter.in.web.dto.response.servey;

import java.time.LocalDateTime;

import com.project.servey.adapter.out.persistence.entity.constant.ServeyType;

import lombok.*;
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class ServeyResponseDto {
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

    public static ServeyResponseDto of(Long serveyId, Long memberId, String title, ServeyType type
    , int perPoint, int limitSubmit, LocalDateTime startdate,LocalDateTime enddate){
        return builder()
        .serveyId(serveyId)
        .memberId(memberId)
        .title(title)
        .perPoint(perPoint)
        .type(type)
        .limitSubmit(limitSubmit)
        .startdate(startdate)
        .enddate(enddate)
        .createdAt(LocalDateTime.now())
        .deleteYn("N")
        .build();
    }
}
