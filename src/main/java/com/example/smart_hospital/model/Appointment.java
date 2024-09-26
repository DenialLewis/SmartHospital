package com.example.smart_hospital.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    private Patient patient;
    
    @ManyToOne
    private Doctor doctor;
    private Date appointmentDate;

    // Getters and Setters
}
