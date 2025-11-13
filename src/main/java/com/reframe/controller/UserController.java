package com.reframe.controller;

import com.reframe.modules.user.dto.UserRequestDTO;
import com.reframe.modules.user.dto.UserResponseDTO;
import com.reframe.modules.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> create(@RequestBody UserRequestDTO dto) {
        UserResponseDTO created = userService.createUser(dto);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> list() {
        List<UserResponseDTO> list = userService.listUsers();
        return ResponseEntity.ok(list);
    }
}
