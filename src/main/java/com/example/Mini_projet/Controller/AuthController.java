package com.example.Mini_projet.Controller;



import com.example.Mini_projet.entity.User;
import com.example.Mini_projet.requests.AuthRequest;
import com.example.Mini_projet.requests.RegisterRequest;
import com.example.Mini_projet.responses.AuthResponse;
import com.example.Mini_projet.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        try {
            User user = authService.register(request);
            String jwt = authService.authenticate(request.getEmail(), request.getPassword());

            return ResponseEntity.ok(new AuthResponse(
                    jwt,
                    user.getId(),
                    user.getFirstname(),
                    user.getLastname(),
                    user.getEmail()
            ));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody AuthRequest request) {
        try {
            String jwt = authService.authenticate(request.getEmail(), request.getPassword());
            // Vous pouvez ajouter plus d'informations utilisateur ici si nécessaire
            return ResponseEntity.ok(new AuthResponse(jwt, "Login successful"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}