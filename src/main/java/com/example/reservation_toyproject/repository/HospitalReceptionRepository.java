package com.example.reservation_toyproject.repository;

import com.example.reservation_toyproject.domain.HospitalReception;
import com.example.reservation_toyproject.domain.QHospitalReception;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

public interface HospitalReceptionRepository extends
        JpaRepository<HospitalReception, Long>,
        QuerydslPredicateExecutor<HospitalReception>,
        QuerydslBinderCustomizer<QHospitalReception>
{

    Page<HospitalReception> findByUserAccount_Name(String name, Pageable pageable);
    Page<HospitalReception> findByUserAccount_PhoneNumberContaining(String phoneNumber, Pageable pageable);
    Page<HospitalReception> findByUserReservation_TxListContaining(String txList, Pageable pageable);
    Page<HospitalReception> findByUserReservation_ReservationStatus(String reservationStatus, Pageable pageable);

    @Override
    default void customize(QuerydslBindings bindings, QHospitalReception root) {
        bindings.excludeUnlistedProperties(true);
        bindings.including(root.userAccount.name, root.userAccount.phoneNumber, root.userReservation.txList, root.userReservation.reservationStatus);
    }
}
