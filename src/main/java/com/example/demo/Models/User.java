package com.example.demo.Models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@EqualsAndHashCode(exclude = {"bookings"})
@Entity
@Table(name = "users") // Renamed table to avoid reserved keywords or conflicts
public class User {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "username")
    private String username;

    @Column(name = "role")
    private String role;

    @Column(name = "otp")
    private String otp;

    @Column(name = "verified")
    private Boolean verified;

    @Column(name = "url")
    private String url;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Booking> bookings = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "cart",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "art_id")
    )
    @ToString.Exclude
    private List<Art> cart;
}
