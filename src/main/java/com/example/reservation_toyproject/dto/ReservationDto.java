package com.example.reservation_toyproject.dto;


import com.example.reservation_toyproject.domain.HospitalReception;
import com.example.reservation_toyproject.domain.UserReservation;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter @Getter
public class ReservationDto {

    private Long id;
    private String hospitalName;
    private String txList;
    private String reservationStatus;
    private UserAccountDto userAccountDto;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    protected ReservationDto() {
    }

    private ReservationDto(Long id, String hospitalName, String txList, String reservationStatus,
                              UserAccountDto userAccountDto, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.hospitalName = hospitalName;
        this.txList = txList;
        this.reservationStatus = reservationStatus;
        this.userAccountDto = userAccountDto;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public static ReservationDto of(Long id, String hospitalName, String txList, String reservationStatus,
                               UserAccountDto userAccountDto, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        return new ReservationDto(id, hospitalName, txList, reservationStatus, userAccountDto, createdAt, modifiedAt);
    }

    public static ReservationDto from(UserReservation entity) {
        return new ReservationDto(
                entity.getId(),
                entity.getHospitalName(),
                entity.getTxList(),
                entity.getReservationStatus(),
                UserAccountDto.from(entity.getUserAccount()),
                entity.getCreatedAt(),
                entity.getModifiedAt()
        );
    }
    public UserReservation toEntity() {
        return UserReservation.of(
                userAccountDto.toEntity(),
                hospitalName,
                txList,
                reservationStatus
        );
    }
}
