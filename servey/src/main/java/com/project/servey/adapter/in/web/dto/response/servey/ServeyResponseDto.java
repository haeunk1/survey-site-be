package com.project.servey.adapter.in.web.dto.response.servey;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.servey.adapter.out.persistence.entity.constant.ServeyType;

import lombok.*;
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServeyResponseDto {
    private Long serveyId;
    private Long memberId;
    private String title;
    private ServeyType type;
    private int perPoint;
    private int limitSubmit;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startdate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime enddate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String deleteYn;

    public static ServeyResponseDto of(Long memberId, String title, ServeyType type
    , int perPoint, int limitSubmit, LocalDateTime startdate,LocalDateTime enddate){
        return builder()
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
