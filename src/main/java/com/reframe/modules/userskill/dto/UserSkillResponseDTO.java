package com.reframe.modules.userskill.dto;

public class UserSkillResponseDTO {

    private Long id;
    private Long userId;
    private String userName;
    private Long skillId;
    private String skillName;

    private Integer currentLevel;
    private Integer desiredLevel;
    private Integer priority;
    private Integer gap; // desired - current

    public UserSkillResponseDTO() {
    }

    public UserSkillResponseDTO(Long id, Long userId, String userName,
                                Long skillId, String skillName,
                                Integer currentLevel, Integer desiredLevel,
                                Integer priority, Integer gap) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.skillId = skillId;
        this.skillName = skillName;
        this.currentLevel = currentLevel;
        this.desiredLevel = desiredLevel;
        this.priority = priority;
        this.gap = gap;
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

    public Long getSkillId() {
        return skillId;
    }

    public String getSkillName() {
        return skillName;
    }

    public Integer getCurrentLevel() {
        return currentLevel;
    }

    public Integer getDesiredLevel() {
        return desiredLevel;
    }

    public Integer getPriority() {
        return priority;
    }

    public Integer getGap() {
        return gap;
    }
}
