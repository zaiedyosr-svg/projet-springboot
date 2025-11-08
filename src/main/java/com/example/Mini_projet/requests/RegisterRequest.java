package com.example.Mini_projet.requests;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String nom;
    private String prenom;
    private String cin;
    private String tel;
    private String adresse;
    private String email;
    private String password;
}