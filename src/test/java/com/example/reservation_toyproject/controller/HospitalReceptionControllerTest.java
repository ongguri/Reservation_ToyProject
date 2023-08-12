package com.example.reservation_toyproject.controller;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@DisplayName("view 컨트롤러 - 접수 확인")
@WebMvcTest(HospitalReceptionController.class)
public class HospitalReceptionControllerTest {

    private final MockMvc mvc;

    public HospitalReceptionControllerTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }


    @DisplayName("[view-GET] 예약 상세 페이지 - 정상 호출")
    @Test
    public void givenNothing_whenRequestingReceptionsView_thenReturnsReceptionsView() throws Exception {
        // Given

        // When & Then
        mvc.perform(get("/receptions"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("receptions/index"))
                .andExpect(model().attributeExists("receptions"));
    }
}
