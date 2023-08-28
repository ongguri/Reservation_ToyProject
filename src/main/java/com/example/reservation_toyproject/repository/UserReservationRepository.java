package com.example.reservation_toyproject.repository;

import com.example.reservation_toyproject.domain.QUserReservation;
import com.example.reservation_toyproject.domain.UserReservation;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

public interface UserReservationRepository extends
        JpaRepository<UserReservation, Long>,
        QuerydslPredicateExecutor<UserReservation>, // UserReservation 엔티티에 있는 모든 필드에 대한 기본 검색 기능 추가
        QuerydslBinderCustomizer<QUserReservation> // 부분 검색을 위함.
{

    Page<UserReservation> findByUserAccount_Name(String name, Pageable pageable);
    Page<UserReservation> findByHospitalNameContaining(String hospitalName, Pageable pageable);
    Page<UserReservation> findByTxListContaining(String txList, Pageable pageable);
    Page<UserReservation> findByReservationStatusContaining(String reservationStatus, Pageable pageable);

    @Override
    default void customize(QuerydslBindings bindings, QUserReservation root) {
        bindings.excludeUnlistedProperties(true); // 정의되지 않은 엔터티 속성들은 바인딩 과정에서 무시되어 실제로 사용되는 속성들만 쿼리에 반영
        bindings.including(root.userAccount.name, root.hospitalName, root.txList, root.reservationStatus);
        bindings.bind(root.hospitalName).first(StringExpression::containsIgnoreCase); // 쿼리 생성문 : like '%${value}% || containsIgnoreCase -> 대소문자 무시하고 검색.
        bindings.bind(root.txList).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.reservationStatus).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.createdAt).first(DateTimeExpression::eq);  // eq -> 정확한 검색.
    }
}
