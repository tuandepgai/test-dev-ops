package com.example.tuan.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class SignupRequest {

    @NotBlank(message = "Username is required")
    @Size(max = 30, message = "Username must be at most 30 characters")
    @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "Username can only contain letters, numbers, and underscore")
    private String userName;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+\\-=[\\]{};':\"\\\\|,.<>/?]).+$",
            message = "Password must contain at least one digit, one uppercase, one lowercase, and one special character"
    )
    private String password;

    @Email(message = "Email should be valid")
    private String email;
}
