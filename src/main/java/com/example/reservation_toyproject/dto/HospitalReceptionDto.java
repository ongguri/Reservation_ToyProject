package com.example.reservation_toyproject.dto;


import com.example.reservation_toyproject.domain.HospitalReception;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class HospitalReceptionDto {

    private Long id;
    private UserAccountDto userAccountDto;
    private UserReservationDto userReservationDto;

    protected HospitalReceptionDto() {
    }

    private HospitalReceptionDto(Long id, UserAccountDto userAccountDto, UserReservationDto userReservationDto) {
        this.id = id;
        this.userAccountDto = userAccountDto;
        this.userReservationDto = userReservationDto;
    }

    public HospitalReceptionDto of(Long id, UserAccountDto userAccountDto, UserReservationDto userReservationDto) {
        return new HospitalReceptionDto(id, userAccountDto, userReservationDto);
    }

    public HospitalReception toEntity() {
        return HospitalReception.of(
                userAccountDto.toEntity(),
                userReservationDto.toEntity()
        );
    }
}
