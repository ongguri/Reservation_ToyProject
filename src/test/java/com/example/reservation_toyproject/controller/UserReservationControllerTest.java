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

@DisplayName("view 컨트롤러 - 예약 확인")
@WebMvcTest(UserReservationController.class)
public class UserReservationControllerTest {

    private final MockMvc mvc;

    public UserReservationControllerTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }

    @DisplayName("[view-GET] 예약 확인 리스트 페이지 - 정상 호출")
    @Test
    public void givenNothing_whenRequestingReservationsView_thenReturnsReservationsView() throws Exception {
        // Given

        // When & Then
        mvc.perform(get("/reservations"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("reservations/index"))
                .andExpect(model().attributeExists("reservations"));
    }

    //    @Disabled("구현 중")
    @DisplayName("[view-GET] 예약 상세 페이지 - 정상 호출")
    @Test
    public void givenNothing_whenRequestingReservationView_thenReturnsReservationView() throws Exception {
        // Given

        // When & Then
        mvc.perform(get("/reservations/kiwanczyk0@upenn.edu"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("reservations/detail"))
                .andExpect(model().attributeExists("reservation"))
                .andExpect(model().attributeExists("hospitalReception"));
    }
}
