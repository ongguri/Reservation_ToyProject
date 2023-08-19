package com.example.reservation_toyproject.service;


import com.example.reservation_toyproject.domain.UserReservation;
import com.example.reservation_toyproject.domain.type.SearchType;
import com.example.reservation_toyproject.dto.UserReservationDto;
import com.example.reservation_toyproject.repository.UserReservationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class ReservationService {

    private final UserReservationRepository userReservationRepository;

    @Transactional(readOnly = true)
    public Page<UserReservationDto> searchUserReservation(SearchType searchType, String searchKeyword, Pageable pageable) {
        if(searchKeyword == null || searchKeyword.isBlank()) {
            return userReservationRepository.findAll(pageable).map(UserReservationDto::from);
        }

        switch (searchType) {
            case USERNAME:
                return userReservationRepository.findByUserAccount_Name(searchKeyword, pageable).map(UserReservationDto::from);
            case EMAIL:
                return userReservationRepository.findByUserAccount_EmailContaining(searchKeyword, pageable).map(UserReservationDto::from);
            case PHONENUMBER:
                return userReservationRepository.findByUserAccount_PhoneNumberContaining(searchKeyword, pageable).map(UserReservationDto::from);
            case BIRTHDAY:
                return userReservationRepository.findByUserAccount_BirthdayContaining(searchKeyword, pageable).map(UserReservationDto::from);
            case HOSPITALNAME:
                return userReservationRepository.findByHospitalNameContaining(searchKeyword, pageable).map(UserReservationDto::from);
            case TXLIST:
                return userReservationRepository.findByTxListContaining(searchKeyword, pageable).map(UserReservationDto::from);
            case RESERVATIONSTATUS:
                return userReservationRepository.findByReservationStatusContaining(searchKeyword, pageable).map(UserReservationDto::from);
        }

        return Page.empty();
    }

    @Transactional(readOnly = true)
    public UserReservationDto getUserReservation(long userReservationId) {
        return userReservationRepository.findById(userReservationId)
                .map(UserReservationDto::from)
                .orElseThrow(() -> new EntityNotFoundException("예약 정보가 없습니다 - userReservationId: " + userReservationId));
    }  // 파라미터를 찍음으로써 로그에 불필요한 내부 데이터가 노출되는데,
    // 이는 운영 및 빠른 로그 분석을 위함.

    public void saveUserReservation(UserReservationDto dto) {
        userReservationRepository.save(dto.toEntity());
    }

    public void updateUserReservation(UserReservationDto dto) {
        try {
            UserReservation userReservation = userReservationRepository.getReferenceById(dto.getId());
            if(dto.getHospitalName() != null) {
                userReservation.setHospitalName(dto.getHospitalName());
            }
            if(dto.getTxList() != null) {
                userReservation.setTxList(dto.getTxList());
            }
            if(dto.getReservationStatus() != null) {
                userReservation.setReservationStatus(dto.getReservationStatus());
            }
            // 트랜잭션이 끝날 때, 영속성 컨텍스트는 userReservation 이 변한것을 감지.
            // 감지한 것에 관한 쿼리를 날리기 때문에 save() 가 없어도 내부적으로 업데이트.
            // save() 메서드를 사용해도 상관없음.
        } catch (EntityNotFoundException e) {
            log.warn("예약 정보 업데이트 실패. 예약 정보를 찾을 수 없습니다 - dto: {}", dto);
        }
    }

    public void deleteUserReservation(long userReservationId) {
        userReservationRepository.deleteById(userReservationId);
    }
}
