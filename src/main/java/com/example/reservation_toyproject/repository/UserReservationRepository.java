package com.example.reservation_toyproject.repository;

import com.example.reservation_toyproject.domain.UserReservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserReservationRepository extends JpaRepository<UserReservation, Long> {

}
