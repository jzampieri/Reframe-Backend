package com.reframe.modules.skill.dto;

import com.reframe.modules.skill.entity.SkillCategory;

public class SkillRequestDTO {

    private String name;
    private String description;
    private SkillCategory category;
    private String futureRoleTag;
    private Integer marketTrendScore;

    public SkillRequestDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SkillCategory getCategory() {
        return category;
    }

    public void setCategory(SkillCategory category) {
        this.category = category;
    }

    public String getFutureRoleTag() {
        return futureRoleTag;
    }

    public void setFutureRoleTag(String futureRoleTag) {
        this.futureRoleTag = futureRoleTag;
    }

    public Integer getMarketTrendScore() {
        return marketTrendScore;
    }

    public void setMarketTrendScore(Integer marketTrendScore) {
        this.marketTrendScore = marketTrendScore;
    }
}
