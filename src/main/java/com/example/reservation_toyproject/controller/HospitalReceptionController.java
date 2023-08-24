package com.example.reservation_toyproject.controller;

import com.example.reservation_toyproject.domain.type.SearchType;
import com.example.reservation_toyproject.response.HospitalReceptionResponse;
import com.example.reservation_toyproject.service.PaginationService;
import com.example.reservation_toyproject.service.ReceptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/receptions")
@Controller
public class HospitalReceptionController {

    private final ReceptionService receptionService;
    private final PaginationService paginationService;

    @GetMapping
    public String receptions(
        @RequestParam(required = false) SearchType searchType,
        @RequestParam(required = false) String searchValue,
        @PageableDefault(size = 5) Pageable pageable,
        ModelMap map
    ) {
        Page<HospitalReceptionResponse> receptions = receptionService.searchHospitalReception(
                searchType, searchValue, pageable).map(HospitalReceptionResponse::from);
        List<Integer> paginationBarNumbers = paginationService.getPaginationBarNumbers(pageable.getPageNumber(), receptions.getTotalPages());

        map.addAttribute("receptions", receptions);
        map.addAttribute("paginationBarNumbers", paginationBarNumbers);

        return "receptions/index";
    }
}
