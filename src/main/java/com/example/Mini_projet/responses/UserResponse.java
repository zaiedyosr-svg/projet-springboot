package com.example.Mini_projet.responses;


import java.util.Set;

public class UserResponse {
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private Set<String> roles;

    // Constructeurs
    public UserResponse() {}

    public UserResponse(Long id, String firstname, String lastname, String email, Set<String> roles) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.roles = roles;
    }

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFirstname() { return firstname; }
    public void setFirstname(String firstname) { this.firstname = firstname; }

    public String getLastname() { return lastname; }
    public void setLastname(String lastname) { this.lastname = lastname; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Set<String> getRoles() { return roles; }
    public void setRoles(Set<String> roles) { this.roles = roles; }
}