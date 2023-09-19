package com.example.reservation_toyproject.dto.response;

import com.example.reservation_toyproject.dto.ReservationDto;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class HospitalReceptionResponse {
    private Long id;
    private String name;
    private String phoneNumber;
    private String txList;
    private LocalDateTime createdAt;
    private String reservationStatus;

    protected HospitalReceptionResponse() {
    }

    private HospitalReceptionResponse(Long id, String name, String phoneNumber, String txList,
        LocalDateTime createdAt, String reservationStatus) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.txList = txList;
        this.createdAt = createdAt;
        this.reservationStatus = reservationStatus;
    }

    public static HospitalReceptionResponse of(Long id, String name, String phoneNumber, String txList,
        LocalDateTime createdAt, String reservationStatus) {
        return new HospitalReceptionResponse(id, name, phoneNumber, txList, createdAt, reservationStatus);
    }

    public static HospitalReceptionResponse from(ReservationDto dto) {

        return new HospitalReceptionResponse(
            dto.getId(),
            dto.getUserAccountDto().getName(),
            dto.getUserAccountDto().getPhoneNumber(),
            dto.getTxList(),
            dto.getCreatedAt(),
            dto.getReservationStatus()
        );
    }
}
