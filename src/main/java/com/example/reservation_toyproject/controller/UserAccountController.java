package com.example.reservation_toyproject.controller;

import com.example.reservation_toyproject.dto.UserAccountDto;
import com.example.reservation_toyproject.dto.request.UserAccountRequest;
import com.example.reservation_toyproject.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/sign")
@Controller
public class UserAccountController {

    private final UserAccountService userAccountService;

    @GetMapping
    public String signInPage() {

        return "sign/sign-in";
    }

    @GetMapping("/sign-up")
    public String signUpPage() {

        return "sign/sign-up";
    }

    @PostMapping("/create-account")
    public String createAccount(UserAccountRequest userAccountRequest) {

        userAccountService.saveUserAccount(userAccountRequest.toDto());

        return "redirect:/sign";
    }
}
