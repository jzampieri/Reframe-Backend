package com.reframe.modules.userskill.dto;

public class UserSkillRequestDTO {

    private Long skillId;
    private Integer currentLevel;
    private Integer desiredLevel;
    private Integer priority;

    public UserSkillRequestDTO() {
    }

    public Long getSkillId() {
        return skillId;
    }

    public void setSkillId(Long skillId) {
        this.skillId = skillId;
    }

    public Integer getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(Integer currentLevel) {
        this.currentLevel = currentLevel;
    }

    public Integer getDesiredLevel() {
        return desiredLevel;
    }

    public void setDesiredLevel(Integer desiredLevel) {
        this.desiredLevel = desiredLevel;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }
}
