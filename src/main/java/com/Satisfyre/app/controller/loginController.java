package com.Satisfyre.app.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class loginController {
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }
}
