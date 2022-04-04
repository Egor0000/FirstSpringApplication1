package com.example.educational.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class PersonProfileCreateDTO {
    private Integer age;
    private String name;
    private String surname;
    private LocalDateTime birthDate;
    private LocalDateTime deathDate;
}
