package com.example.Mini_projet.services;




import com.example.Mini_projet.entity.User;
import com.example.Mini_projet.repository.UserRepository;
import com.example.Mini_projet.responses.UserResponse;
import com.example.Mini_projet.requests.UserUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // 🔹 Lister tous les users
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // 🔹 Obtenir un user par ID (avec réponse formatée)
    public UserResponse getUserByIdResponse(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        return mapToResponse(user);
    }

    // 🔹 Mettre à jour un user (ADMIN uniquement)
    public UserResponse updateUser(Long id, UserUpdateRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        user.setNom(request.getNom());
        user.setPrenom(request.getPrenom());
        user.setCin(request.getCin());
        user.setTel(request.getTel());
        user.setAdresse(request.getAdresse());

        User updatedUser = userRepository.save(user);
        return mapToResponse(updatedUser);
    }

    // 🔹 Supprimer un user
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    // 🔹 Méthode privée pour convertir User → UserResponse
    private UserResponse mapToResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .nom(user.getNom())
                .prenom(user.getPrenom())
                .cin(user.getCin())
                .tel(user.getTel())
                .adresse(user.getAdresse())
                .email(user.getEmail())
                .role(user.getRole().getName())
                .build();
    }
}

