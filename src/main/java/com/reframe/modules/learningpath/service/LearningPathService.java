package com.reframe.modules.learningpath.service;

import com.reframe.modules.learningpath.dto.LearningPathItemDTO;
import com.reframe.modules.learningpath.dto.LearningPathResponseDTO;
import com.reframe.modules.learningpath.entity.LearningPath;
import com.reframe.modules.learningpath.entity.LearningPathItem;
import com.reframe.modules.learningpath.repository.LearningPathItemRepository;
import com.reframe.modules.learningpath.repository.LearningPathRepository;
import com.reframe.modules.user.entity.User;
import com.reframe.modules.user.repository.UserRepository;
import com.reframe.modules.userskill.entity.UserSkill;
import com.reframe.modules.userskill.repository.UserSkillRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LearningPathService {

    private final UserRepository userRepository;
    private final UserSkillRepository userSkillRepository;
    private final LearningPathRepository learningPathRepository;
    private final LearningPathItemRepository learningPathItemRepository;

    public LearningPathService(UserRepository userRepository,
                               UserSkillRepository userSkillRepository,
                               LearningPathRepository learningPathRepository,
                               LearningPathItemRepository learningPathItemRepository) {
        this.userRepository = userRepository;
        this.userSkillRepository = userSkillRepository;
        this.learningPathRepository = learningPathRepository;
        this.learningPathItemRepository = learningPathItemRepository;
    }

    public LearningPathResponseDTO generateForUser(Long userId, String targetRole) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado para o id " + userId));

        List<UserSkill> userSkills = userSkillRepository.findByUserId(userId);

        List<UserSkill> withGap = userSkills.stream()
                .filter(us -> us.getDesiredLevel() != null && us.getCurrentLevel() != null)
                .filter(us -> us.getDesiredLevel() > us.getCurrentLevel())
                .collect(Collectors.toList());

        if (withGap.isEmpty()) {
            throw new RuntimeException("Usuário não possui gaps de skill para gerar trilha");
        }

        String title = "Trilha de desenvolvimento para " + (targetRole != null ? targetRole : "novo papel");
        LearningPath path = new LearningPath(user, title, targetRole);
        LearningPath savedPath = learningPathRepository.save(path);

        List<UserSkill> sorted = withGap.stream()
                .sorted(Comparator.comparingDouble(this::scoreForUserSkill).reversed())
                .collect(Collectors.toList());

        int index = 1;
        for (UserSkill us : sorted) {
            int gap = us.getDesiredLevel() - us.getCurrentLevel();
            gap = Math.max(gap, 0);

            Integer priority = us.getPriority() != null ? us.getPriority() : 1;

            LearningPathItem item = new LearningPathItem(
                    savedPath,
                    us.getSkill(),
                    index,
                    gap,
                    priority
            );
            learningPathItemRepository.save(item);
            index++;
        }

        List<LearningPathItem> items = learningPathItemRepository
                .findByLearningPathIdOrderByOrderIndexAsc(savedPath.getId());

        savedPath.setItems(items);

        return toResponse(savedPath);
    }

    public List<LearningPathResponseDTO> listByUser(Long userId) {
        List<LearningPath> paths = learningPathRepository.findByUserId(userId);
        return paths.stream()
                .map(this::toResponseWithItemsLoaded)
                .collect(Collectors.toList());
    }

    private double scoreForUserSkill(UserSkill us) {
        int gap = Math.max(us.getDesiredLevel() - us.getCurrentLevel(), 0);
        int priority = us.getPriority() != null ? us.getPriority() : 1;
        int marketTrend = us.getSkill().getMarketTrendScore() != null
                ? us.getSkill().getMarketTrendScore()
                : 50; // default

        return gap * 2 + priority + (marketTrend / 20.0);
    }

    private LearningPathResponseDTO toResponseWithItemsLoaded(LearningPath path) {
        List<LearningPathItem> items = learningPathItemRepository
                .findByLearningPathIdOrderByOrderIndexAsc(path.getId());
        path.setItems(items);
        return toResponse(path);
    }

    private LearningPathResponseDTO toResponse(LearningPath path) {
        List<LearningPathItemDTO> itemDTOs = path.getItems() != null
                ? path.getItems().stream()
                .map(item -> new LearningPathItemDTO(
                        item.getId(),
                        item.getSkill().getId(),
                        item.getSkill().getName(),
                        item.getOrderIndex(),
                        item.getGapAtCreation(),
                        item.getPriorityAtCreation(),
                        item.isCompleted()
                ))
                .collect(Collectors.toList())
                : List.of();

        return new LearningPathResponseDTO(
                path.getId(),
                path.getUser().getId(),
                path.getUser().getName(),
                path.getTitle(),
                path.getTargetRole(),
                path.getCreatedAt(),
                path.getProgressPercent(),
                itemDTOs
        );
    }
}
