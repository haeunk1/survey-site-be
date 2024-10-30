package com.project.servey.application.command.servey;

import com.project.servey.adapter.in.web.dto.request.servey.ServeyListRequestDto;
import com.project.servey.adapter.out.persistence.entity.constant.ListOrderType;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class FindServeyListCommand {
    private String title;
    private ListOrderType orderType;

    // factory method
    public static FindServeyListCommand of(ServeyListRequestDto serveyListRequestDto) {
        return FindServeyListCommand.builder()
                .title(serveyListRequestDto.getTitle())
                .orderType(serveyListRequestDto.getOrderType())
                .build();
    }
}
