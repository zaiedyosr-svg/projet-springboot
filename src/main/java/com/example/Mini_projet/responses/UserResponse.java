package com.example.Mini_projet.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String nom;
    private String prenom;
    private String cin;
    private String email;
    private String tel;
    private String adresse;
    private String role; // par exemple "ADMIN" ou "USER"
}