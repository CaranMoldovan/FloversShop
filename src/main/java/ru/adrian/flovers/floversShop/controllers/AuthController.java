package ru.adrian.flovers.floversShop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class AuthController {
    @GetMapping("/auth")
    public String login(){
        return "auth";
    }

}
