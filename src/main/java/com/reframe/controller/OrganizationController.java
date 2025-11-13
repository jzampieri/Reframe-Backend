package com.reframe.controller;

import com.reframe.modules.entity.Organization;
import com.reframe.modules.repository.OrganizationRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/organizations")
public class OrganizationController {

    private final OrganizationRepository organizationRepository;

    public OrganizationController(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    @PostMapping
    public ResponseEntity<Organization> create(@RequestBody Organization org) {
        Organization saved = organizationRepository.save(org);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public ResponseEntity<List<Organization>> list() {
        List<Organization> all = organizationRepository.findAll();
        return ResponseEntity.ok(all);
    }
}
