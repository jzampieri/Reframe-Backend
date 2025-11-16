package com.reframe.modules.badge.service;

import com.reframe.modules.badge.dto.BadgeRequestDTO;
import com.reframe.modules.badge.dto.BadgeResponseDTO;
import com.reframe.modules.badge.entity.Badge;
import com.reframe.modules.badge.entity.UserBadge;
import com.reframe.modules.badge.repository.BadgeRepository;
import com.reframe.modules.badge.repository.UserBadgeRepository;
import com.reframe.modules.user.entity.User;
import com.reframe.modules.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BadgeService {

    private final BadgeRepository badgeRepository;
    private final UserBadgeRepository userBadgeRepository;
    private final UserRepository userRepository;

    public BadgeService(BadgeRepository badgeRepository,
                        UserBadgeRepository userBadgeRepository,
                        UserRepository userRepository) {
        this.badgeRepository = badgeRepository;
        this.userBadgeRepository = userBadgeRepository;
        this.userRepository = userRepository;
    }

    public BadgeResponseDTO create(BadgeRequestDTO dto) {

        badgeRepository.findByName(dto.getName()).ifPresent(b -> {
            throw new RuntimeException("Já existe um badge com nome: " + dto.getName());
        });

        Badge badge = new Badge();
        badge.setName(dto.getName());
        badge.setDescription(dto.getDescription());
        badge.setConditionText(dto.getConditionText());
        badge.setIcon(dto.getIcon());

        Badge saved = badgeRepository.save(badge);

        return new BadgeResponseDTO(
                saved.getId(),
                saved.getName(),
                saved.getDescription(),
                saved.getConditionText(),
                saved.getIcon(),
                null
        );
    }

    public List<BadgeResponseDTO> listAll() {
        return badgeRepository.findAll().stream()
                .map(b -> new BadgeResponseDTO(
                        b.getId(),
                        b.getName(),
                        b.getDescription(),
                        b.getConditionText(),
                        b.getIcon(),
                        null
                ))
                .collect(Collectors.toList());
    }

    public BadgeResponseDTO awardToUser(Long userId, Long badgeId, int xpReward) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado para o id " + userId));

        Badge badge = badgeRepository.findById(badgeId)
                .orElseThrow(() -> new RuntimeException("Badge não encontrado para o id " + badgeId));

        userBadgeRepository.findByUserIdAndBadgeId(userId, badgeId)
                .ifPresent(ub -> {
                    throw new RuntimeException("Usuário já possui este badge");
                });

        UserBadge userBadge = new UserBadge(user, badge);
        UserBadge savedUserBadge = userBadgeRepository.save(userBadge);

        int newXp = (user.getXp() != null ? user.getXp() : 0) + xpReward;
        user.setXp(newXp);
        user.setLevel(calculateLevel(newXp));
        userRepository.save(user);

        return new BadgeResponseDTO(
                badge.getId(),
                badge.getName(),
                badge.getDescription(),
                badge.getConditionText(),
                badge.getIcon(),
                savedUserBadge.getAwardedAt()
        );
    }

    public List<BadgeResponseDTO> listByUser(Long userId) {
        List<UserBadge> list = userBadgeRepository.findByUserId(userId);
        return list.stream()
                .map(ub -> new BadgeResponseDTO(
                        ub.getBadge().getId(),
                        ub.getBadge().getName(),
                        ub.getBadge().getDescription(),
                        ub.getBadge().getConditionText(),
                        ub.getBadge().getIcon(),
                        ub.getAwardedAt()
                ))
                .collect(Collectors.toList());
    }

    private int calculateLevel(int xp) {
        int base = xp / 100;
        return Math.max(base, 1);
    }
}
