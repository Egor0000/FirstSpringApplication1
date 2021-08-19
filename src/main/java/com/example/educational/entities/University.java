package com.example.educational.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity(name="universities")
public class University{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "short_name")
    private String shortName;

    @Column(name = "registration_number")
    private String registrationNumber;

    @OneToMany(mappedBy = "university", cascade = CascadeType.ALL)
    private List<Faculty> faculties;
}
