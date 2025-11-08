package com.example.Mini_projet.repository;

import com.example.Mini_projet.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    // 🔍 Chercher un rôle par son nom
    Optional<Role> findByName(Role.RoleName name);
}