package com.reframe.controller;

import com.reframe.modules.badge.dto.BadgeRequestDTO;
import com.reframe.modules.badge.dto.BadgeResponseDTO;
import com.reframe.modules.badge.service.BadgeService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/badges")
public class BadgeController {

    private final BadgeService badgeService;

    public BadgeController(BadgeService badgeService) {
        this.badgeService = badgeService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<BadgeResponseDTO> create(@RequestBody BadgeRequestDTO dto) {
        BadgeResponseDTO created = badgeService.create(dto);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public ResponseEntity<List<BadgeResponseDTO>> listAll() {
        return ResponseEntity.ok(badgeService.listAll());
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('ORG_ADMIN')")
    @PostMapping("/award/user/{userId}/badge/{badgeId}")
    public ResponseEntity<BadgeResponseDTO> awardBadge(
            @PathVariable Long userId,
            @PathVariable Long badgeId,
            @RequestParam(defaultValue = "50") int xpReward
    ) {
        BadgeResponseDTO awarded = badgeService.awardToUser(userId, badgeId, xpReward);
        return ResponseEntity.ok(awarded);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BadgeResponseDTO>> listByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(badgeService.listByUser(userId));
    }
}
