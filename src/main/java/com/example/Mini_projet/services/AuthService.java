package com.example.Mini_projet.services;

import com.example.Mini_projet.entity.ERole;
import com.example.Mini_projet.entity.Role;
import com.example.Mini_projet.entity.User;
import com.example.Mini_projet.repository.RoleRepository;
import com.example.Mini_projet.repository.UserRepository;
import com.example.Mini_projet.requests.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    public User register(RegisterRequest request) {
        // Vérifier si l'email existe déjà
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Error: Email is already in use!");
        }

        // Créer un nouvel utilisateur
        User user = new User(
                request.getFirstname(),
                request.getLastname(),
                request.getEmail(),
                passwordEncoder.encode(request.getPassword())
        );

        // Assigner les rôles
        Set<Role> roles = assignRoles(request.getRole());
        user.setRoles(roles);

        return userRepository.save(user);
    }

    public String authenticate(String email, String password) {
        try {
            // Authentifier l'utilisateur
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );

            // Récupérer l'utilisateur
            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            // Générer le token JWT
            return jwtService.generateToken(UserDetailsImpl.build(user));

        } catch (AuthenticationException e) {
            throw new RuntimeException("Invalid email or password");
        }
    }

    /**
     * Assigner les rôles à l'utilisateur
     */
    private Set<Role> assignRoles(Set<String> strRoles) {
        Set<Role> roles = new HashSet<>();

        if (strRoles == null || strRoles.isEmpty()) {
            // Par défaut, ROLE_USER
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: ROLE_USER not found. Please create roles first."));
            roles.add(userRole);
        } else {
            // Assigner les rôles spécifiés
            strRoles.forEach(role -> {
                switch (role.toLowerCase()) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: ROLE_ADMIN not found. Please create roles first."));
                        roles.add(adminRole);
                        break;
                    case "user":
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: ROLE_USER not found. Please create roles first."));
                        roles.add(userRole);
                }
            });
        }

        return roles;
    }
}