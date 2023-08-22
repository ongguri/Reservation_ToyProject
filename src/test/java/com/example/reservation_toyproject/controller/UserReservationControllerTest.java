package com.example.reservation_toyproject.controller;

import com.example.reservation_toyproject.service.ReservationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@DisplayName("view 컨트롤러 - 예약 확인")
@WebMvcTest(UserReservationController.class)
public class UserReservationControllerTest {

    private final MockMvc mvc;

    @MockBean
    private ReservationService reservationService;

    public UserReservationControllerTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }

    @DisplayName("[view-GET] 예약 확인 리스트 페이지 - 정상 호출")
    @Test
    public void givenNothing_whenRequestingReservationsView_thenReturnsReservationsView() throws Exception {
        // Given
        given(reservationService.searchUserReservation(eq(null), eq(null), any(Pageable.class))).willReturn(
            Page.empty());

        // When & Then
        mvc.perform(get("/reservations"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("reservations/index"))
                .andExpect(model().attributeExists("reservations"));
        then(reservationService).should().searchUserReservation(eq(null), eq(null), any(Pageable.class));
    }
}
