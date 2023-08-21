package com.example.reservation_toyproject.response;

import com.example.reservation_toyproject.dto.ReservationDto;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class UserReservationResponse {
    private Long id;
    private String name;
    private String hospitalName;
    private String txList;
    private LocalDateTime createdAt;
    private String reservationStatus;

    protected UserReservationResponse() {
    }

    private UserReservationResponse(Long id, String name, String hospitalName, String txList,
        LocalDateTime createdAt, String reservationStatus) {
        this.id = id;
        this.name = name;
        this.hospitalName = hospitalName;
        this.txList = txList;
        this.createdAt = createdAt;
        this.reservationStatus = reservationStatus;
    }

    public UserReservationResponse of(Long id, String name, String hospitalName, String txList,
        LocalDateTime createdAt, String reservationStatus) {
        return new UserReservationResponse(id, name, hospitalName, txList, createdAt, reservationStatus);
    }

    public static UserReservationResponse from(ReservationDto dto) {

        return new UserReservationResponse(
            dto.getId(),
            dto.getUserAccountDto().getName(),
            dto.getHospitalName(),
            dto.getTxList(),
            dto.getCreatedAt(),
            dto.getReservationStatus()
        );
    }
}
