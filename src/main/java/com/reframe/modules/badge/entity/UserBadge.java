package com.reframe.modules.badge.entity;

import com.reframe.modules.user.entity.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_badges",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "badge_id"}))
public class UserBadge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private User user;

    @ManyToOne(optional = false)
    private Badge badge;

    @Column(nullable = false)
    private LocalDateTime awardedAt;

    public UserBadge() {
    }

    public UserBadge(User user, Badge badge) {
        this.user = user;
        this.badge = badge;
        this.awardedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Badge getBadge() {
        return badge;
    }

    public LocalDateTime getAwardedAt() {
        return awardedAt;
    }
}
