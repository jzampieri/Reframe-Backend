package com.reframe.modules.skill.dto;

import com.reframe.modules.skill.entity.SkillCategory;

public class SkillResponseDTO {

    private Long id;
    private String name;
    private String description;
    private SkillCategory category;
    private String futureRoleTag;
    private Integer marketTrendScore;

    public SkillResponseDTO() {
    }

    public SkillResponseDTO(Long id, String name, String description, SkillCategory category,
                            String futureRoleTag, Integer marketTrendScore) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.futureRoleTag = futureRoleTag;
        this.marketTrendScore = marketTrendScore;
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

    public SkillCategory getCategory() {
        return category;
    }

    public String getFutureRoleTag() {
        return futureRoleTag;
    }

    public Integer getMarketTrendScore() {
        return marketTrendScore;
    }
}
