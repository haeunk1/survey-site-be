package com.project.servey.application.command.servey;

import lombok.*;
import java.time.LocalDateTime;

import com.project.servey.adapter.in.web.dto.response.servey.ServeyResponseDto;
import com.project.servey.adapter.out.persistence.entity.constant.ServeyType;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class CreateServeyCommand {
    private String userId;
    private String title;
    private ServeyType type;
    private int category;
    private int perPoint;
    private int limitSubmit;
    private LocalDateTime startdate;
    private LocalDateTime enddate;
    private String deleteYn;
    
    // factory method
    public static CreateServeyCommand of(ServeyResponseDto serveyResponseDto) {
        return CreateServeyCommand.builder()
                .userId(serveyResponseDto.getUserId())
                .title(serveyResponseDto.getTitle())
                .type(serveyResponseDto.getType())
                .category(serveyResponseDto.getCategory())
                .perPoint(serveyResponseDto.getPerPoint())
                .limitSubmit(serveyResponseDto.getLimitSubmit())
                .startdate(serveyResponseDto.getStartdate())
                .enddate(serveyResponseDto.getEnddate())
                .deleteYn(serveyResponseDto.getDeleteYn())
                .build();
    }
}
