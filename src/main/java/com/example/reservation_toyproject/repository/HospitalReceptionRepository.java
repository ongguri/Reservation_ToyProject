package com.example.reservation_toyproject.repository;

import com.example.reservation_toyproject.domain.HospitalReception;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalReceptionRepository extends JpaRepository<HospitalReception, Long> {

    Page<HospitalReception> findByUserAccount_Name(String name, Pageable pageable);
    Page<HospitalReception> findByUserAccount_PhoneNumberContaining(String phoneNumber, Pageable pageable);
    Page<HospitalReception> findByUserReservation_TxListContaining(String txList, Pageable pageable);
    Page<HospitalReception> findByUserReservation_ReservationStatus(String reservationStatus, Pageable pageable);

}
