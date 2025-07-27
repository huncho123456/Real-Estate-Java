package com.Satisfyre.app.controller;


import com.Satisfyre.app.dto.LoginRequest;
import com.Satisfyre.app.dto.RegistrationRequest;
import com.Satisfyre.app.dto.Response;
import com.Satisfyre.app.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

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

}
