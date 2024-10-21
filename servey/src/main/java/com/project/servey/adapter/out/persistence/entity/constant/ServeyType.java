package com.project.servey.adapter.out.persistence.entity.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ServeyType {
    OX("OX","OX"),
    CHOICE2("CHOICE2","2지선다"),
    CHOICE3("CHOICE3","3지선다"),
    CHOICE4("CHOICE4","4지선다"),
    CHOICE5("CHOICE5","5지선다"),
    SHORTANSWER("SHORTANSWER","단답형")
    ;
    private final String code;
    private final String description;
}
