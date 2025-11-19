package com.example.tuan.controller;

import com.example.tuan.dto.request.LoginRequest;
import com.example.tuan.dto.request.SignupRequest;
import com.example.tuan.dto.response.ApiResponse;
import com.example.tuan.dto.response.LoginResponse;
import com.example.tuan.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<LoginResponse>> signup(@RequestBody SignupRequest request) {
        ApiResponse<LoginResponse> response = authService.signup(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<?>> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}
