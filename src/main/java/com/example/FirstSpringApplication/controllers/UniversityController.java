package com.example.FirstSpringApplication.controllers;

import com.example.FirstSpringApplication.dto.UniversityDTO;
import com.example.FirstSpringApplication.entities.University;
import com.example.FirstSpringApplication.services.impl.UniversityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/service/university")
public class UniversityController {
    private final UniversityService universityService;

    @PostMapping("/save")
    public HttpStatus saveUniversity(@RequestBody UniversityDTO universityDTO){
        return universityService.saveUniversity(universityDTO);
    }

    @GetMapping("/all")
    public List<UniversityDTO> getAll(){
        return universityService.getAll();
    }

    @GetMapping("/getById/{id}")
    public UniversityDTO getById(@PathVariable Long id){
        return universityService.getById(id);
    }

    @PutMapping("/update")
    public UniversityDTO update(@RequestBody UniversityDTO universityDTO){
        return universityService.update(universityDTO);
    }

    @DeleteMapping("/deleteById/{id}")
    public HttpStatus deleteById(   @PathVariable Long id){
        return universityService.deleteById(id);
    }
}
