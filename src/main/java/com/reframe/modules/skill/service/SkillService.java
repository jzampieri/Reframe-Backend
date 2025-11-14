package com.reframe.modules.skill.service;

import com.reframe.modules.skill.dto.SkillRequestDTO;
import com.reframe.modules.skill.dto.SkillResponseDTO;
import com.reframe.modules.skill.entity.Skill;
import com.reframe.modules.skill.repository.SkillRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SkillService {

    private final SkillRepository skillRepository;

    public SkillService(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    public SkillResponseDTO create(SkillRequestDTO dto) {

        // regra simples: evitar skills duplicadas
        skillRepository.findByName(dto.getName()).ifPresent(s -> {
            throw new RuntimeException("Já existe uma skill com nome: " + dto.getName());
        });

        Skill skill = new Skill();
        skill.setName(dto.getName());
        skill.setDescription(dto.getDescription());
        skill.setCategory(dto.getCategory());
        skill.setFutureRoleTag(dto.getFutureRoleTag());
        skill.setMarketTrendScore(dto.getMarketTrendScore());

        Skill saved = skillRepository.save(skill);

        return toResponse(saved);
    }

    public List<SkillResponseDTO> listAll() {
        return skillRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public SkillResponseDTO getById(Long id) {
        Skill skill = skillRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Skill não encontrada para o id " + id));
        return toResponse(skill);
    }

    private SkillResponseDTO toResponse(Skill skill) {
        return new SkillResponseDTO(
                skill.getId(),
                skill.getName(),
                skill.getDescription(),
                skill.getCategory(),
                skill.getFutureRoleTag(),
                skill.getMarketTrendScore()
        );
    }
}
