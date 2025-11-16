package com.reframe.modules.learningpath.dto;

import java.time.LocalDateTime;
import java.util.List;

public class LearningPathResponseDTO {

    private Long id;
    private Long userId;
    private String userName;
    private String title;
    private String targetRole;
    private LocalDateTime createdAt;
    private Integer progressPercent;
    private List<LearningPathItemDTO> items;

    public LearningPathResponseDTO() {
    }

    public LearningPathResponseDTO(Long id, Long userId, String userName,
                                   String title, String targetRole,
                                   LocalDateTime createdAt,
                                   Integer progressPercent,
                                   List<LearningPathItemDTO> items) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.title = title;
        this.targetRole = targetRole;
        this.createdAt = createdAt;
        this.progressPercent = progressPercent;
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getTitle() {
        return title;
    }

    public String getTargetRole() {
        return targetRole;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Integer getProgressPercent() {
        return progressPercent;
    }

    public List<LearningPathItemDTO> getItems() {
        return items;
    }
}
