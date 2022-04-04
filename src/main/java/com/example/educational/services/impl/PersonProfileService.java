package com.example.educational.services.impl;

import com.example.educational.dto.PersonProfileCreateDTO;
import com.example.educational.dto.PersonProfileDto;
import com.example.educational.entities.PersonProfile;

import java.util.List;
import java.util.Optional;

public interface PersonProfileService {
    PersonProfileDto save(PersonProfileCreateDTO personProfileDto);
    PersonProfileDto update(PersonProfileDto personProfileDto);
    boolean delete(Integer profileId);
    List<PersonProfileDto> getAll();
    PersonProfileDto getById(Integer id) throws Exception;
}
