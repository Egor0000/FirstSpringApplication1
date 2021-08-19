package com.example.educational.services.impl;

import com.example.educational.dto.FacultyDTO;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface FacultyService {
    HttpStatus save(FacultyDTO facultyDTO);

    List<FacultyDTO> getAll();

    FacultyDTO getById(Long id);

    FacultyDTO update(FacultyDTO FacultyDTO);

    HttpStatus deleteById(Long id);
}
