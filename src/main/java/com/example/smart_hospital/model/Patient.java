package com.example.smart_hospital.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private Date dob; // Date of Birth
    private String phone;

    // Getters and Setters
}