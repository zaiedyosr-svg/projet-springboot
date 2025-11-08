package com.example.Mini_projet.services;



import com.example.Mini_projet.entity.Role;
import com.example.Mini_projet.entity.Token;
import com.example.Mini_projet.entity.TokenType;
import com.example.Mini_projet.entity.User;
import com.example.Mini_projet.repository.RoleRepository;
import com.example.Mini_projet.repository.TokenRepository;
import com.example.Mini_projet.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public String register(User user) {
        Role userRole = roleRepository.findByName(Role.RoleName.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Role non trouvé"));

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(userRole);
        User savedUser = userRepository.save(user);

        String jwtToken = jwtService.generateToken(savedUser);
        saveUserToken(savedUser, jwtToken);

        return jwtToken;
    }

    public String login(String email, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String jwtToken = jwtService.generateToken(user);
        saveUserToken(user, jwtToken);
        return jwtToken;
    }

    private void saveUserToken(User user, String jwtToken) {
        Token token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }
}

