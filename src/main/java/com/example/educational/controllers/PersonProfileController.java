package com.example.educational.controllers;

import com.example.educational.dto.PersonProfileCreateDTO;
import com.example.educational.dto.PersonProfileDto;
import com.example.educational.entities.PersonProfile;
import com.example.educational.services.impl.PersonProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/service/person-profile")
@RequiredArgsConstructor
public class PersonProfileController {
    private final PersonProfileService personProfileService;

    @PostMapping("/")
    public PersonProfileDto save(@RequestBody PersonProfileCreateDTO personProfileDto){
        return personProfileService.save(personProfileDto);
    }

    @GetMapping("/{id}")
    public PersonProfileDto getById(@PathVariable Integer id) throws Exception{
        return personProfileService.getById(id);
    }
}
