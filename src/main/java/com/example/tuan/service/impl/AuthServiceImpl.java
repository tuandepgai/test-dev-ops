package com.example.tuan.service.impl;

import com.example.tuan.constant.UserStatusEnum;
import com.example.tuan.dto.request.LoginRequest;
import com.example.tuan.dto.request.SignupRequest;
import com.example.tuan.dto.response.ApiResponse;
import com.example.tuan.dto.response.LoginResponse;
import com.example.tuan.entity.UserProfile;
import com.example.tuan.repository.UserProfileRepository;
import com.example.tuan.service.AuthService;
import com.example.tuan.utils.JwtUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserProfileRepository userProfileRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    @Transactional
    public ApiResponse<LoginResponse> signup(SignupRequest request) {
        if (userProfileRepository.findByUserName(request.getUserName()).isPresent()) {
            return new ApiResponse<>(HttpStatus.BAD_REQUEST, "Username already exists", null);
        }

        UserProfile user = UserProfile.builder()
                .userName(request.getUserName())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .role("USER")
                .status(UserStatusEnum.ACTIVE)
                .createdAt(LocalDateTime.now())
                .build();

        userProfileRepository.save(user);

        String token = jwtUtil.generateToken(user.getUserName(), user.getRole());

        LoginResponse loginResponse = new LoginResponse(user.getId(), user.getUserName(), user.getRole(), token);

        return new ApiResponse<>(HttpStatus.OK, "Signup successful", loginResponse);
    }

    @Override
    public ApiResponse<?> login(LoginRequest request) {
        return userProfileRepository.findByUserName(request.getUserName())
                .map(user -> {
                    if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                        String token = jwtUtil.generateToken(user.getUserName(), user.getRole());
                        LoginResponse loginResponse = new LoginResponse(user.getId(), user.getUserName(), user.getRole(), token);
                        return new ApiResponse<>(HttpStatus.OK, "Login successful", loginResponse);
                    } else {
                        return new ApiResponse<>(HttpStatus.BAD_REQUEST, "Invalid password", null);
                    }
                })
                .orElse(new ApiResponse<>(HttpStatus.BAD_REQUEST, "User not found", null));
    }

}
