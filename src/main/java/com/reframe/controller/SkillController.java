package com.reframe.controller;

import com.reframe.modules.skill.dto.SkillRequestDTO;
import com.reframe.modules.skill.dto.SkillResponseDTO;
import com.reframe.modules.skill.service.SkillService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/skills")
public class SkillController {

    private final SkillService skillService;

    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<SkillResponseDTO> create(@RequestBody SkillRequestDTO dto) {
        SkillResponseDTO created = skillService.create(dto);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public ResponseEntity<List<SkillResponseDTO>> list() {
        return ResponseEntity.ok(skillService.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SkillResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(skillService.getById(id));
    }
}
