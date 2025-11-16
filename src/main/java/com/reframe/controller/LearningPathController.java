package com.reframe.controller;

import com.reframe.modules.learningpath.dto.LearningPathResponseDTO;
import com.reframe.modules.learningpath.service.LearningPathService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/{userId}/learning-path")
public class LearningPathController {

    private final LearningPathService learningPathService;

    public LearningPathController(LearningPathService learningPathService) {
        this.learningPathService = learningPathService;
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('ORG_ADMIN')")
    @PostMapping("/generate")
    public ResponseEntity<LearningPathResponseDTO> generate(
            @PathVariable Long userId,
            @RequestParam(required = false) String targetRole
    ) {
        LearningPathResponseDTO path = learningPathService.generateForUser(userId, targetRole);
        return ResponseEntity.ok(path);
    }

    @GetMapping
    public ResponseEntity<List<LearningPathResponseDTO>> listByUser(@PathVariable Long userId) {
        List<LearningPathResponseDTO> paths = learningPathService.listByUser(userId);
        return ResponseEntity.ok(paths);
    }
}
