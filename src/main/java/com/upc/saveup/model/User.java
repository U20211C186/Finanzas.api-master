package com.upc.saveup.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "email", length = 30, nullable = false)
    private String email;
    @Column(name = "name", length = 20, nullable = false)
    private String name;
    @Column(name = "last_name", length = 20, nullable = false)
    private String lastName;
    @Column(name = "password", length = 12, nullable = false)
    private String password;
    @Column(name = "repeat_password", length = 12, nullable = false)
    private String repeatPassword;
}
