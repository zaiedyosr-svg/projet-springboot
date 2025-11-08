package com.example.Mini_projet.requests;

import lombok.Data;

@Data
public class UserUpdateRequest {
    private String nom;
    private String prenom;
    private String cin;
    private String tel;
    private String adresse;
}
