package com.nizar.SahTech.entities.doctors;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Doctors")
@Data
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String password;

}
