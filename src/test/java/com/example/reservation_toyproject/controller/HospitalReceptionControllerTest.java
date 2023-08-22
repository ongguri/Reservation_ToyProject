package com.example.reservation_toyproject.controller;

import com.example.reservation_toyproject.service.ReceptionService;
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

@DisplayName("view 컨트롤러 - 접수 확인")
@WebMvcTest(HospitalReceptionController.class)
public class HospitalReceptionControllerTest {

    private final MockMvc mvc;

    @MockBean
    private ReceptionService receptionService;

    public HospitalReceptionControllerTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }

    @DisplayName("[view-GET] 접수 내역 페이지 - 정상 호출")
    @Test
    public void givenNothing_whenRequestingReceptionsView_thenReturnsReceptionsView() throws Exception {
        // Given
        given(receptionService.searchHospitalReception(eq(null), eq(null), any(Pageable.class))).willReturn(
            Page.empty());

        // When & Then
        mvc.perform(get("/receptions"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("receptions/index"))
                .andExpect(model().attributeExists("receptions"));
        then(receptionService).should().searchHospitalReception(eq(null), eq(null), any(Pageable.class));
    }
}
