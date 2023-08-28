package com.example.reservation_toyproject.domain.type;

import lombok.Getter;

/**
 * 환자명
 * 연락처
 * 진료항목
 * 접수상태
 */

@Getter
public enum ReceptionSearchType {
    USERNAME("환자명"),
    PHONENUMBER("연락처"),
    TXLIST("진료항목"),
    RESERVATIONSTATUS("진료상태");

    private final String description;

    ReceptionSearchType(String description) {
        this.description = description;
    }
}
