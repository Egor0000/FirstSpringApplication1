package com.example.educational.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonProfileDto {
    private Integer id;
    private Integer age;
    private String name;
    private String surname;
    private LocalDateTime birthDate;
    private LocalDateTime deathDate;
}
