package com.example.reservation_toyproject.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/receptions")
@Controller
public class HospitalReceptionController {


    @GetMapping
    public String reservations(ModelMap map) {
        map.addAttribute("receptions", "receptionsData"); // 임시값
        return "receptions/index";
    }
}
