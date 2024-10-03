package com.project.servey.domain;

import java.time.LocalDateTime;

import lombok.*;

@Builder
@Getter
//@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Servey {
    private Long serveyId;
    private String userId;
    private String title;
    private int type;
    private int category;
    private int perPoint;
    private int limitSubmit;
    private LocalDateTime startdate;
    private LocalDateTime enddate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
