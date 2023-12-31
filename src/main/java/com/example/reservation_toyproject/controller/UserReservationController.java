package com.example.reservation_toyproject.controller;

import com.example.reservation_toyproject.domain.type.ReservationSearchType;
import com.example.reservation_toyproject.dto.response.UserReservationResponse;
import com.example.reservation_toyproject.service.PaginationService;
import com.example.reservation_toyproject.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/reservations")
@Controller
public class UserReservationController {

    private final ReservationService reservationService;
    private final PaginationService paginationService;

    @GetMapping
    public String reservations(
        @RequestParam(required = false) ReservationSearchType reservationSearchType,
        @RequestParam(required = false) String searchValue,
        @PageableDefault(size = 5, sort = "createdAt", direction = Direction.DESC) Pageable pageable,
        ModelMap map
    ) {
        Page<UserReservationResponse> reservations = reservationService.searchUserReservation(
                reservationSearchType, searchValue, pageable).map(UserReservationResponse::from);
        List<Integer> barNumbers = paginationService.getPaginationBarNumbers(pageable.getPageNumber(), reservations.getTotalPages());

        map.addAttribute("reservations", reservations);
        map.addAttribute("paginationBarNumbers", barNumbers);
        map.addAttribute("reservationSearchTypes", ReservationSearchType.values());

        return "reservations/index";
    }
}
