package com.example.Mini_projet.requests;


public class UserUpdateRequest {
    private String firstname;
    private String lastname;
    private String email;

    // Getters et Setters
    public String getFirstname() { return firstname; }
    public void setFirstname(String firstname) { this.firstname = firstname; }

    public String getLastname() { return lastname; }
    public void setLastname(String lastname) { this.lastname = lastname; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}