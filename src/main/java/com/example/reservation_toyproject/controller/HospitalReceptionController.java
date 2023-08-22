package com.example.reservation_toyproject.controller;


import com.example.reservation_toyproject.domain.type.SearchType;
import com.example.reservation_toyproject.response.HospitalReceptionResponse;
import com.example.reservation_toyproject.service.ReceptionService;
import com.example.reservation_toyproject.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@RequestMapping("/receptions")
@Controller
public class HospitalReceptionController {

    private final ReceptionService receptionService;

    @GetMapping
    public String receptions(
        @RequestParam(required = false) SearchType searchType,
        @RequestParam(required = false) String searchValue,
        @PageableDefault(size = 15) Pageable pageable,
        ModelMap map
    ) {
        map.addAttribute("receptions", receptionService.searchHospitalReception(
            searchType, searchValue, pageable).map(HospitalReceptionResponse::from));
        return "receptions/index";
    }
}
