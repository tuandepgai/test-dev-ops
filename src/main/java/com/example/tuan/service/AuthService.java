package com.example.tuan.service;

import com.example.tuan.dto.request.LoginRequest;
import com.example.tuan.dto.request.SignupRequest;
import com.example.tuan.dto.response.ApiResponse;
import com.example.tuan.dto.response.LoginResponse;

public interface AuthService {
    ApiResponse<LoginResponse> signup(SignupRequest request);

    ApiResponse<?> login(LoginRequest request);
}
