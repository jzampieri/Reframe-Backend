package com.reframe.modules.learningpath.entity;

import com.reframe.modules.skill.entity.Skill;
import jakarta.persistence.*;

@Entity
@Table(name = "learning_path_items")
public class LearningPathItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "learning_path_id")
    private LearningPath learningPath;

    @ManyToOne(optional = false)
    private Skill skill;

    private Integer orderIndex;

    private Integer gapAtCreation;

    private Integer priorityAtCreation;

    private boolean completed;

    public LearningPathItem() {
    }

    public LearningPathItem(LearningPath learningPath, Skill skill,
                            Integer orderIndex, Integer gapAtCreation,
                            Integer priorityAtCreation) {
        this.learningPath = learningPath;
        this.skill = skill;
        this.orderIndex = orderIndex;
        this.gapAtCreation = gapAtCreation;
        this.priorityAtCreation = priorityAtCreation;
        this.completed = false;
    }

    public Long getId() {
        return id;
    }

    public LearningPath getLearningPath() {
        return learningPath;
    }

    public void setLearningPath(LearningPath learningPath) {
        this.learningPath = learningPath;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public Integer getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(Integer orderIndex) {
        this.orderIndex = orderIndex;
    }

    public Integer getGapAtCreation() {
        return gapAtCreation;
    }

    public void setGapAtCreation(Integer gapAtCreation) {
        this.gapAtCreation = gapAtCreation;
    }

    public Integer getPriorityAtCreation() {
        return priorityAtCreation;
    }

    public void setPriorityAtCreation(Integer priorityAtCreation) {
        this.priorityAtCreation = priorityAtCreation;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
