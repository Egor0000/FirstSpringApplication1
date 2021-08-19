package com.example.educational.controllers;

import com.example.educational.dto.FacultyDTO;
import com.example.educational.services.impl.FacultyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/service/faculty")
@RequiredArgsConstructor
public class FacultyController {
    private final FacultyService facultyService;

    @PostMapping("/save")
    public HttpStatus save(@RequestBody FacultyDTO facultyDTO){
        return facultyService.save(facultyDTO);
    }

    @GetMapping("/all")
    public List<FacultyDTO> getAll(){
        return facultyService.getAll();
    }

    @GetMapping("/getById/{id}")
    public FacultyDTO getById(@PathVariable Long id){
        return facultyService.getById(id);
    }

    @PutMapping("/update")
    public FacultyDTO update(@RequestBody FacultyDTO facultyDTO){
        return facultyService.update(facultyDTO);
    }

    @DeleteMapping("/delete/{id}")
    public HttpStatus deleteById(@PathVariable Long id) {
        return facultyService.deleteById(id);
    }
}
