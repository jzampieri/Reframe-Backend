package com.reframe.modules.skill.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "skills")
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    private SkillCategory category;

    private String futureRoleTag;

    private Integer marketTrendScore; // 0 a 100

    public Skill() {
    }

    public Skill(String name, String description, SkillCategory category, String futureRoleTag, Integer marketTrendScore) {
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
