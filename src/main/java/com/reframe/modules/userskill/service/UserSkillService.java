package com.reframe.modules.userskill.service;

import com.reframe.modules.repository.OrganizationRepository;
import com.reframe.modules.skill.entity.Skill;
import com.reframe.modules.skill.repository.SkillRepository;
import com.reframe.modules.user.entity.User;
import com.reframe.modules.user.repository.UserRepository;
import com.reframe.modules.userskill.dto.UserSkillRequestDTO;
import com.reframe.modules.userskill.dto.UserSkillResponseDTO;
import com.reframe.modules.userskill.entity.UserSkill;
import com.reframe.modules.userskill.repository.UserSkillRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserSkillService {

    private final UserRepository userRepository;
    private final SkillRepository skillRepository;
    private final UserSkillRepository userSkillRepository;

    public UserSkillService(UserRepository userRepository,
                            SkillRepository skillRepository,
                            UserSkillRepository userSkillRepository) {
        this.userRepository = userRepository;
        this.skillRepository = skillRepository;
        this.userSkillRepository = userSkillRepository;
    }

    public UserSkillResponseDTO upsertUserSkill(Long userId, UserSkillRequestDTO dto) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado para o id " + userId));

        Skill skill = skillRepository.findById(dto.getSkillId())
                .orElseThrow(() -> new RuntimeException("Skill não encontrada para o id " + dto.getSkillId()));

        validateLevel(dto.getCurrentLevel(), "currentLevel");
        if (dto.getDesiredLevel() != null) {
            validateLevel(dto.getDesiredLevel(), "desiredLevel");
        }

        UserSkill userSkill = userSkillRepository.findByUserIdAndSkillId(userId, dto.getSkillId())
                .orElseGet(() -> new UserSkill(user, skill, dto.getCurrentLevel(), dto.getDesiredLevel(), dto.getPriority()));

        userSkill.setCurrentLevel(dto.getCurrentLevel());
        userSkill.setDesiredLevel(dto.getDesiredLevel());
        userSkill.setPriority(dto.getPriority());

        UserSkill saved = userSkillRepository.save(userSkill);

        return toResponse(saved);
    }

    public List<UserSkillResponseDTO> listByUser(Long userId) {
        List<UserSkill> list = userSkillRepository.findByUserId(userId);
        return list.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private void validateLevel(Integer level, String fieldName) {
        if (level == null) {
            throw new RuntimeException("Campo " + fieldName + " é obrigatório");
        }
        if (level < 1 || level > 5) {
            throw new RuntimeException("Campo " + fieldName + " deve estar entre 1 e 5");
        }
    }

    private UserSkillResponseDTO toResponse(UserSkill us) {
        Integer current = us.getCurrentLevel();
        Integer desired = us.getDesiredLevel();
        Integer gap = 0;
        if (current != null && desired != null) {
            int diff = desired - current;
            gap = Math.max(diff, 0);
        }

        return new UserSkillResponseDTO(
                us.getId(),
                us.getUser().getId(),
                us.getUser().getName(),
                us.getSkill().getId(),
                us.getSkill().getName(),
                us.getCurrentLevel(),
                us.getDesiredLevel(),
                us.getPriority(),
                gap
        );
    }
}
