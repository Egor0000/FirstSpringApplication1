package com.example.educational.dto;

import lombok.Data;

@Data
public class FacultyDTO {
    private Long id;
    private String name;
    private String shortName;
    private String address;
    private Long universityId;
}
