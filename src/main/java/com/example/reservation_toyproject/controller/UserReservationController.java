package com.example.reservation_toyproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/reservations")
@Controller
public class UserReservationController {

    @GetMapping
    public String reservations(ModelMap map) {
        map.addAttribute("reservations", List.of()); // 임시값
        return "reservations/index";
    }

    @GetMapping("/{user-reservation-email}")
    public String reservation(ModelMap map) {
        map.addAttribute("reservation", "reservationData"); // TODO: 나중에 실제 데이터 넣기
        map.addAttribute("hptReception", "hptReceptionData"); // TODO: 나중에 실제 데이터 넣기

        return "reservations/detail";
    }
}
