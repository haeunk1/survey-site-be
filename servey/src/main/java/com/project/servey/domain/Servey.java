package com.project.servey.domain;

import java.time.LocalDateTime;

import com.project.servey.adapter.out.persistence.entity.constant.ServeyType;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Servey {
    private Long serveyId;
    private int memberId;
    private String title;
    private ServeyType type;
    private int category;
    private int perPoint;
    private int limitSubmit;
    private LocalDateTime startdate;
    private LocalDateTime enddate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String deleteYn;
}
