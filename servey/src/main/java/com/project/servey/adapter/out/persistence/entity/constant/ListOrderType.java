package com.project.servey.adapter.out.persistence.entity.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ListOrderType {
    POINT_ASC("POINT_ASC","제공 포인트 오름차순"),
    POINT_DESC("POINT_ASC","제공 포인트 내림차순"),
    ENDDATE_ASC("ENDDATE_ASC","마감일 오름차순"),
    ENDDATE_DESC("ENDDATE_DESC","마감일 내림차순"),
    ;
    private final String code;
    private final String description;
}
