package com.example.reservation_toyproject.repository;

import com.example.reservation_toyproject.domain.UserReservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserReservationRepository extends JpaRepository<UserReservation, Long> {

    Page<UserReservation> findByUserAccount_Name(String name, Pageable pageable);
    Page<UserReservation> findByHospitalNameContaining(String hospitalName, Pageable pageable);
    Page<UserReservation> findByTxListContaining(String txList, Pageable pageable);
    Page<UserReservation> findByReservationStatusContaining(String reservationStatus, Pageable pageable);
}
