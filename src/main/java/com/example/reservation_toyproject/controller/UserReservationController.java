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
}
