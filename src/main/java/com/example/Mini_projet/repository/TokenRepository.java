package com.example.Mini_projet.repository;

import com.example.Mini_projet.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {

    // 🔍 Récupérer tous les tokens actifs d’un user
    List<Token> findAllByUserIdAndExpiredFalseAndRevokedFalse(Long userId);

    // 🔍 Trouver un token spécifique
    Optional<Token> findByToken(String token);
}