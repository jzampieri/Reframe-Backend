package com.reframe.modules.auth.dto;

import com.reframe.modules.user.entity.UserRole;

public class AuthResponseDTO {

    private String token;
    private Long userId;
    private String name;
    private String email;
    private UserRole role;

    public AuthResponseDTO() {
    }

    public AuthResponseDTO(String token, Long userId, String name, String email, UserRole role) {
        this.token = token;
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public Long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public UserRole getRole() {
        return role;
    }
}
