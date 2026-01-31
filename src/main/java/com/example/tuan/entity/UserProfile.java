package com.example.tuan.entity;


import com.example.tuan.constant.UserStatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Entity
@Table(name = "user_profile")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name", length = 256)
    private String userName;

    @Column(name = "password", length = 256)
    private String password;

    @Column(name = "email", length = 256)
    private String email;

    @Column(name = "status", length = 25)
    @Enumerated(EnumType.STRING)
    private UserStatusEnum status;

    @Column(name = "delete_by", length = 25)
    private String deleteBy;

    @Column(name = "role", length = 50)
    private String role;

    @Column(name = "created_at", length = 256)
    private LocalDateTime createdAt;

    @Column(name = "deleted_at", length = 256)
    private LocalDateTime deletedAt;

    @Column(name = "updated_at", length = 256)
    private LocalDateTime updatedAt;
}
