package com.example.reservation_toyproject.service;

import com.example.reservation_toyproject.domain.type.SearchType;
import com.example.reservation_toyproject.dto.ReservationDto;
import com.example.reservation_toyproject.repository.HospitalReceptionRepository;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class ReceptionService {

    private final HospitalReceptionRepository hospitalReceptionRepository;

    @Transactional(readOnly = true)
    public Page<ReservationDto> searchHospitalReception(SearchType searchType, String searchKeyword, Pageable pageable) {
        if(searchKeyword == null || searchKeyword.isBlank()) {
            return hospitalReceptionRepository.findAll(pageable).map(ReservationDto::from);
        }

        switch (searchType) {
            case USERNAME:
                return hospitalReceptionRepository.findByUserAccount_Name(searchKeyword, pageable).map(
                    ReservationDto::from);
            case PHONENUMBER:
                return hospitalReceptionRepository.findByUserAccount_PhoneNumberContaining(searchKeyword, pageable).map(
                    ReservationDto::from);
            case TXLIST:
                return hospitalReceptionRepository.findByUserReservation_TxListContaining(searchKeyword, pageable).map(
                    ReservationDto::from);
            case RESERVATIONSTATUS:
                return hospitalReceptionRepository.findByUserReservation_ReservationStatus(searchKeyword, pageable).map(
                    ReservationDto::from);
        }

        return Page.empty();
    }

    @Transactional(readOnly = true)
    public ReservationDto getHospitalReception(long hospitalReceptionId) {
        return hospitalReceptionRepository.findById(hospitalReceptionId)
                .map(ReservationDto::from)
                .orElseThrow(() -> new EntityNotFoundException("예약 정보가 없습니다 - userReservationId: " + hospitalReceptionId));
    }

    public void deleteHospitalReception(long hospitalReceptionId) {
        hospitalReceptionRepository.deleteById(hospitalReceptionId);
    }
}
