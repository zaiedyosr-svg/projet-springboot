package com.example.Mini_projet.repository;

import com.example.Mini_projet.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // 🔍 Méthode personnalisée pour chercher un user par email
    Optional<User> findByEmail(String email);
}