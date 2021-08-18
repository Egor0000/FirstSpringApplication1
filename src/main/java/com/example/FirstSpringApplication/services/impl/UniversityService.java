package com.example.FirstSpringApplication.services.impl;

import com.example.FirstSpringApplication.dto.UniversityDTO;
import com.example.FirstSpringApplication.entities.University;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface UniversityService {
    HttpStatus saveUniversity(UniversityDTO universityDTO);

    List<UniversityDTO> getAll();

    UniversityDTO getById(Long id);

    HttpStatus deleteById(Long id);

    UniversityDTO update(UniversityDTO universityDTO);
}
