package com.reframe.modules.userskill.entity;

import com.reframe.modules.skill.entity.Skill;
import com.reframe.modules.user.entity.User;
import jakarta.persistence.*;

@Entity
@Table(name = "user_skills",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "skill_id"}))
public class UserSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "skill_id")
    private Skill skill;

    @Column(nullable = false)
    private Integer currentLevel;

    private Integer desiredLevel;

    private Integer priority;

    public UserSkill() {
    }

    public UserSkill(User user, Skill skill, Integer currentLevel, Integer desiredLevel, Integer priority) {
        this.user = user;
        this.skill = skill;
        this.currentLevel = currentLevel;
        this.desiredLevel = desiredLevel;
        this.priority = priority;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
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
