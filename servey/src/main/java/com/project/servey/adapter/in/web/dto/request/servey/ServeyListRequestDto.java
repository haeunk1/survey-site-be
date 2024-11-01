package com.project.servey.adapter.in.web.dto.request.servey;

import com.project.servey.adapter.out.persistence.entity.constant.ListOrderType;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ServeyListRequestDto {
    private String title;
    private ListOrderType orderType;

    public static ServeyListRequestDto of(String title, ListOrderType orderType){
        return builder()
        .title(title)
        .orderType(orderType)
        .build();
    }
}