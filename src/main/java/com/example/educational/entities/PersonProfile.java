package com.example.educational.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "person_profiles")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "age")
    private Integer age;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "birthDate")
    private LocalDateTime birthDate;

    @Column(name = "deathDate")
    private LocalDateTime deathDate;
}
