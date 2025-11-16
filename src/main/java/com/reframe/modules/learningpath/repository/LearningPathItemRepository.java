package com.reframe.modules.learningpath.repository;

import com.reframe.modules.learningpath.entity.LearningPathItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LearningPathItemRepository extends JpaRepository<LearningPathItem, Long> {

    List<LearningPathItem> findByLearningPathIdOrderByOrderIndexAsc(Long learningPathId);
}
