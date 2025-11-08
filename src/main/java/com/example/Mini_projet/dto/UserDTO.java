package com.example.Mini_projet.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String nom;
    private String prenom;
    private String cin;
    private String tel;
    private String adresse;
    private String email;
    private String role;
}

