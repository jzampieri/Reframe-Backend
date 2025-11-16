package com.reframe.modules.learningpath.entity;

import com.reframe.modules.user.entity.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "learning_paths")
public class LearningPath {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private User user;

    @Column(nullable = false)
    private String title;

    private String targetRole;

    private LocalDateTime createdAt;

    private Integer progressPercent;

    @OneToMany(mappedBy = "learningPath", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LearningPathItem> items;

    public LearningPath() {
    }

    public LearningPath(User user, String title, String targetRole) {
        this.user = user;
        this.title = title;
        this.targetRole = targetRole;
        this.createdAt = LocalDateTime.now();
        this.progressPercent = 0;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
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

    public void setProgressPercent(Integer progressPercent) {
        this.progressPercent = progressPercent;
    }

    public List<LearningPathItem> getItems() {
        return items;
    }

    public void setItems(List<LearningPathItem> items) {
        this.items = items;
    }
}
