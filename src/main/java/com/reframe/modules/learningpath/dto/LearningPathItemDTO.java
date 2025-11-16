package com.reframe.modules.learningpath.dto;

public class LearningPathItemDTO {

    private Long id;
    private Long skillId;
    private String skillName;
    private Integer orderIndex;
    private Integer gapAtCreation;
    private Integer priorityAtCreation;
    private boolean completed;

    public LearningPathItemDTO() {
    }

    public LearningPathItemDTO(Long id, Long skillId, String skillName,
                               Integer orderIndex, Integer gapAtCreation,
                               Integer priorityAtCreation, boolean completed) {
        this.id = id;
        this.skillId = skillId;
        this.skillName = skillName;
        this.orderIndex = orderIndex;
        this.gapAtCreation = gapAtCreation;
        this.priorityAtCreation = priorityAtCreation;
        this.completed = completed;
    }

    public Long getId() {
        return id;
    }

    public Long getSkillId() {
        return skillId;
    }

    public String getSkillName() {
        return skillName;
    }

    public Integer getOrderIndex() {
        return orderIndex;
    }

    public Integer getGapAtCreation() {
        return gapAtCreation;
    }

    public Integer getPriorityAtCreation() {
        return priorityAtCreation;
    }

    public boolean isCompleted() {
        return completed;
    }
}
