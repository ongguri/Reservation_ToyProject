package com.example.reservation_toyproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/sign")
@Controller
public class UserLoginController {

    @GetMapping
    public String signInPage() {

        return "sign/sign-in";
    }

    @GetMapping("/sign-up")
    public String signUpPage() {

        return "sign/sign-up";
    }

    @PostMapping("/create-account")
    public String createAccount() {

        return "redirect:/sign";
    }
}
