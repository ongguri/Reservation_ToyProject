package com.example.reservation_toyproject.domain.type;

import lombok.Getter;

/**
 * 환자명
 * 병원명
 * 진료항목
 * 접수상태
 */

@Getter
public enum ReservationSearchType {
    USERNAME("환자명"),
    HOSPITALNAME("병원명"),
    TXLIST("진료항목"),
    RESERVATIONSTATUS("진료상태");

    private final String description;

    ReservationSearchType(String description) {
        this.description = description;
    }
}
