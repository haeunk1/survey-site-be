package com.project.servey.adapter.in.web.dto.response.servey;


import java.time.LocalDateTime;

import com.project.servey.adapter.out.persistence.entity.constant.ServeyType;

import lombok.*;
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class ServeyListResponseDto {
    private String name;
    private String title;
    private ServeyType type;
    private int perPoint;
    private int limitSubmit;
    private LocalDateTime startdate;
    private LocalDateTime enddate;
    private Long questionCnt;
    //private int submitCnt; //추가예정
    
}
