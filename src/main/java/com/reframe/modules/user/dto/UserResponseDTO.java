package com.reframe.modules.user.dto;

import com.reframe.modules.user.entity.UserRole;

public class UserResponseDTO {

    private Long id;
    private String name;
    private String email;
    private UserRole role;
    private String organizationName;

    public UserResponseDTO() {
    }

    public UserResponseDTO(Long id, String name, String email, UserRole role, String organizationName) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
        this.organizationName = organizationName;
    }

    public Long getId() {
        return id;
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

    public String getOrganizationName() {
        return organizationName;
    }
}
