package com.example.FirstSpringApplication.services.impl;

import com.example.FirstSpringApplication.dto.FacultyDTO;
import com.example.FirstSpringApplication.dto.UniversityDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

public interface FacultyService {
    HttpStatus save(FacultyDTO facultyDTO);

    List<FacultyDTO> getAll();

    FacultyDTO getById(Long id);

    FacultyDTO update(FacultyDTO FacultyDTO);

    HttpStatus deleteById(Long id);
}
