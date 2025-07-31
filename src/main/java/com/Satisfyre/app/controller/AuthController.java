package com.Satisfyre.app.controller;


import com.Satisfyre.app.config.dotenvConfig;
import com.Satisfyre.app.dto.LoginRequest;
import com.Satisfyre.app.dto.RegistrationRequest;
import com.Satisfyre.app.dto.Response;
import com.Satisfyre.app.entity.UserEntity;
import com.Satisfyre.app.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    String BASEURL = dotenvConfig.get("BASEURL");

    @PostMapping("/register")
    public ResponseEntity<Response> registerUser(@RequestBody @Valid RegistrationRequest request){
        return ResponseEntity.ok(userService.registerUser(request));
    }
    @PostMapping("/login")
    public ResponseEntity<Response> loginUser(@RequestBody @Valid LoginRequest request){
        return ResponseEntity.ok(userService.loginUser(request));
    }
    @GetMapping("/verify")
    public Response verify(@RequestParam("token") String token) {
        return userService.verifyToken(token);
    }

    @GetMapping("/me/referral-link")
    public ResponseEntity<Map<String, String>> getReferralLink(@AuthenticationPrincipal UserEntity user) {
        String referralLink = BASEURL +"/register?ref=" + user.getReferralCode();
        return ResponseEntity.ok(Map.of(
                "referralCode", user.getReferralCode(),
                "referralLink", referralLink
        ));
    }

}
