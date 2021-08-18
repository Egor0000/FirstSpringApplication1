package com.example.FirstSpringApplication.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "faculties")
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "short_name")
    private String shortName;

    @Column(name = "address")
    private String address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="university_id", referencedColumnName = "id")
    private University university;
}
