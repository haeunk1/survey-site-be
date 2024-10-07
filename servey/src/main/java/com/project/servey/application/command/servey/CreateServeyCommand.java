package com.project.servey.application.command.servey;

import lombok.*;
import java.time.LocalDateTime;

import com.project.servey.adapter.in.web.dto.response.servey.ServeyResponseDto;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class CreateServeyCommand {
    private String userId;
    private String title;
    private int type;
    private int category;
    private int perPoint;
    private int limitSubmit;
    private LocalDateTime startdate;
    private LocalDateTime enddate;
    

    // factory method
    public static CreateServeyCommand of(ServeyResponseDto serveyResponseDto) {
        return CreateServeyCommand.builder()
                .userId(serveyResponseDto.getUserId())
                .title(serveyResponseDto.getTitle())
                .category(serveyResponseDto.getCategory())
                .perPoint(serveyResponseDto.getPerPoint())
                .limitSubmit(serveyResponseDto.getLimitSubmit())
                .startdate(serveyResponseDto.getStartdate())
                .enddate(serveyResponseDto.getEnddate())
                .build();
    }
}
