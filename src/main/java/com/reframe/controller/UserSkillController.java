package com.reframe.controller;

import com.reframe.modules.userskill.dto.UserSkillRequestDTO;
import com.reframe.modules.userskill.dto.UserSkillResponseDTO;
import com.reframe.modules.userskill.service.UserSkillService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/{userId}/skills")
public class UserSkillController {

    private final UserSkillService userSkillService;

    public UserSkillController(UserSkillService userSkillService) {
        this.userSkillService = userSkillService;
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('ORG_ADMIN')")
    @PostMapping
    public ResponseEntity<UserSkillResponseDTO> upsertUserSkill(
            @PathVariable Long userId,
            @RequestBody UserSkillRequestDTO dto
    ) {
        UserSkillResponseDTO response = userSkillService.upsertUserSkill(userId, dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<UserSkillResponseDTO>> listByUser(@PathVariable Long userId) {
        List<UserSkillResponseDTO> list = userSkillService.listByUser(userId);
        return ResponseEntity.ok(list);
    }
}
