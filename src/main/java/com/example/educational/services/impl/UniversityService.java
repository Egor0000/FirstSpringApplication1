package com.example.educational.services.impl;

import com.example.educational.dto.UniversityDTO;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface UniversityService {
    HttpStatus saveUniversity(UniversityDTO universityDTO);

    List<UniversityDTO> getAll();

    UniversityDTO getById(Long id);

    HttpStatus deleteById(Long id);

    UniversityDTO update(UniversityDTO universityDTO);
}
