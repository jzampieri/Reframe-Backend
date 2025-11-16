package com.reframe.modules.badge.dto;

import java.time.LocalDateTime;

public class BadgeResponseDTO {

    private Long id;
    private String name;
    private String description;
    private String conditionText;
    private String icon;

    private LocalDateTime awardedAt;

    public BadgeResponseDTO() {
    }

    public BadgeResponseDTO(Long id, String name, String description,
                            String conditionText, String icon, LocalDateTime awardedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.conditionText = conditionText;
        this.icon = icon;
        this.awardedAt = awardedAt;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getConditionText() {
        return conditionText;
    }

    public String getIcon() {
        return icon;
    }

    public LocalDateTime getAwardedAt() {
        return awardedAt;
    }
}
