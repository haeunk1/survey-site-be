package com.project.servey.adapter.out.persistence.entity.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PointTransactionType {
    REWARD("REWARD","적립"),
    SPEND("SPEND","사용"),
    RECHARGE("RECHARGE","충전"),
    ;
    private final String code;
    private final String description;
}
