package com.reframe.modules.user.service;

import com.reframe.modules.entity.Organization;
import com.reframe.modules.repository.OrganizationRepository;
import com.reframe.modules.user.dto.UserRequestDTO;
import com.reframe.modules.user.dto.UserResponseDTO;
import com.reframe.modules.user.entity.User;
import com.reframe.modules.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final OrganizationRepository organizationRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       OrganizationRepository organizationRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.organizationRepository = organizationRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponseDTO createUser(UserRequestDTO dto) {

        Organization org = null;
        if (dto.getOrganizationId() != null) {
            org = organizationRepository.findById(dto.getOrganizationId())
                    .orElseThrow(() -> new RuntimeException("Organização não encontrada para o id " + dto.getOrganizationId()));
        }

        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(dto.getRole());
        user.setOrganization(org);

        User saved = userRepository.save(user);

        String orgName = (org != null) ? org.getName() : null;

        return new UserResponseDTO(
                saved.getId(),
                saved.getName(),
                saved.getEmail(),
                saved.getRole(),
                orgName
        );
    }

    public List<UserResponseDTO> listUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> {
                    String orgName = user.getOrganization() != null
                            ? user.getOrganization().getName()
                            : null;
                    return new UserResponseDTO(
                            user.getId(),
                            user.getName(),
                            user.getEmail(),
                            user.getRole(),
                            orgName
                    );
                })
                .collect(Collectors.toList());
    }
}
