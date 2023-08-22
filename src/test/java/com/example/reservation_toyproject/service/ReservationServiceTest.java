package com.example.reservation_toyproject.service;

import com.example.reservation_toyproject.domain.UserAccount;
import com.example.reservation_toyproject.domain.UserReservation;
import com.example.reservation_toyproject.domain.type.SearchType;
import com.example.reservation_toyproject.dto.UserAccountDto;
import com.example.reservation_toyproject.dto.ReservationDto;
import com.example.reservation_toyproject.repository.HospitalReceptionRepository;
import com.example.reservation_toyproject.repository.UserReservationRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

@DisplayName("비즈니스 로직 - 예약/접수")
@ExtendWith(MockitoExtension.class)
class ReservationServiceTest {

    @InjectMocks private ReservationService sut;

    @Mock private UserReservationRepository userReservationRepository;

    @DisplayName("검색 없이 예약정보를 검색하면, 예약 조회 페이지를 반환한다.")
    @Test
    void givenNoSearchParameters_whenSearchingReservations_thenReturnReservationPage() {
        // Given
        Pageable pageable = Pageable.ofSize(20);
        given(userReservationRepository.findAll(pageable)).willReturn(Page.empty());

        // When
        Page<ReservationDto> userReservation = sut.searchUserReservation(null, null, pageable);

        // Then
        assertThat(userReservation).isEmpty();
        then(userReservationRepository).should().findAll(pageable);
    }

    @DisplayName("검색어와 함께 예약정보를 검색하면, 예약 조회 페이지를 반환한다.")
    @Test
    void givenSearchParameters_whenSearchingReservations_thenReturnReservationPage() {
        // Given
        SearchType searchType = SearchType.USERNAME;
        String searchKeyword = "김용선";
        Pageable pageable = Pageable.ofSize(20);
        given(userReservationRepository.findByUserAccount_Name(searchKeyword, pageable)).willReturn(Page.empty());

        // When
        Page<ReservationDto> userReservation = sut.searchUserReservation(searchType, searchKeyword, pageable);

        // Then
        assertThat(userReservation).isEmpty();
        then(userReservationRepository).should().findByUserAccount_Name(searchKeyword, pageable);
    }

    @DisplayName("예약 정보를 조회하면, 예약 정보를 반환한다.")
    @Test
    void givenReservationEmail_whenSearchingReservation_thenReturnReservation() {
        // Given
        Long userReservationId = 1L;
        UserReservation reservation = createUserReservation();
        given(userReservationRepository.findById(userReservationId)).willReturn(Optional.of(reservation));

        // When
        ReservationDto dto = sut.getUserReservation(userReservationId);

        // Then
        assertThat(dto)
                .hasFieldOrPropertyWithValue("hospitalName", reservation.getHospitalName())
                .hasFieldOrPropertyWithValue("txList", reservation.getTxList())
                .hasFieldOrPropertyWithValue("reservationStatus", reservation.getReservationStatus());
        then(userReservationRepository).should().findById(userReservationId);
    }

    @DisplayName("없는 예약 정보를 조회하면, 예외를 던진다.")
    @Test
    void givenNoneExistentReservationID_whenSearchingReservation_thenThrowsException() {
        // Given
        Long reservationId = 0L;
        given(userReservationRepository.findById(reservationId)).willReturn(Optional.empty());

        // When
        Throwable t = catchThrowable(() -> sut.getUserReservation(reservationId));

        // Then
        assertThat(t)
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("예약 정보가 없습니다 - userReservationId: " + reservationId);
        then(userReservationRepository).should().findById(reservationId);
    }

    @DisplayName("예약 정보를 입력하면, 예약 정보를 생성한다")
    @Test
    void givenReservationInfo_whenSavingReservation_thenSaveReservation() {
        // given
        ReservationDto dto = createUserReservationDto();
        given(userReservationRepository.save(any(UserReservation.class))).willReturn(createUserReservation());

        // when
        sut.saveUserReservation(dto);

        // then
        then(userReservationRepository).should().save(any(UserReservation.class));
    }

    @DisplayName("예약정보의 수정 정보를 입력하면, 예약 정보를 수정한다.")
    @Test
    void givenModifiedReservationInfo_whenUpdatingReservation_thenUpdateReservation() {
        // given
        UserReservation userReservation = createUserReservation();
        ReservationDto dto = createUserReservationDto("동탄소아과", "소아 진료", "접수 완료");
        given(userReservationRepository.getReferenceById(dto.getId())).willReturn(userReservation);

        // when
        sut.updateUserReservation(dto);

        // then
        assertThat(userReservation)
                .hasFieldOrPropertyWithValue("hospitalName", dto.getHospitalName())
                .hasFieldOrPropertyWithValue("txList", dto.getTxList())
                .hasFieldOrPropertyWithValue("reservationStatus", dto.getReservationStatus());
        then(userReservationRepository).should().getReferenceById(dto.getId());
    }

    @DisplayName("없는 예약정보의 수정 정보를 입력하면, 경고 로그를 찍고 아무 것도 하지 않는다.")
    @Test
    void givenNoneExistentReservationInfo_whenUpdatingReservation_thenLogsWarningAndDoesNothing() {
        // Given
        ReservationDto dto = createUserReservationDto("동탄소아과", "소아 진료", "접수 완료");
        given(userReservationRepository.getReferenceById(dto.getId())).willThrow(EntityNotFoundException.class);

        // When
        sut.updateUserReservation(dto);

        // Then
        then(userReservationRepository).should().getReferenceById(dto.getId());
    }

    @DisplayName("예약정보의 ID를 입력하면, 예약정보를 삭제한다")
    @Test
    void givenReservationId_whenDeletingReservation_thenDeletesReservation() {
        // given
        Long reservationId = 1L;
        willDoNothing().given(userReservationRepository).deleteById(reservationId);

        // when
        sut.deleteUserReservation(1L);

        // then
        then(userReservationRepository).should().deleteById(reservationId);
    }


    private UserAccount createUserAccount() {
        return UserAccount.of(
                "rkd@naver.com",
                "2984",
                "김용선",
                "010-1234-9999",
                "1996-12-34",
                "mail"
        );
    }

    private UserReservation createUserReservation() {
        return UserReservation.of(
                createUserAccount(),
                "강남소아과",
                "영유아 검진",
                "접수 대기"
        );
    }

    private ReservationDto createUserReservationDto() {
        return createUserReservationDto("강남소아과", "영유아 검진", "접수 대기");
    }

    private ReservationDto createUserReservationDto(String hospitalName, String txList, String reservationStatus) {
        return ReservationDto.of(
                1L,
                hospitalName,
                txList,
                reservationStatus,
                createUserAccountDto(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }

    private UserAccountDto createUserAccountDto() {
        return UserAccountDto.of(
                "kang@naver.com",
                "1234",
                "강선용",
                "010-1234-2222",
                "1920-10-11",
                "mail",
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }

}