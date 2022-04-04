package com.example.educational.services.impl.impl;

import com.example.educational.dto.PersonProfileCreateDTO;
import com.example.educational.dto.PersonProfileDto;
import com.example.educational.entities.PersonProfile;
import com.example.educational.repositories.PersonProfileRepository;
import com.example.educational.services.impl.PersonProfileService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonProfileServiceImpl implements PersonProfileService {
    private final ModelMapper modelMapper;

    private final PersonProfileRepository repository;

    @Override
    public PersonProfileDto save(PersonProfileCreateDTO personProfileDto) {
        PersonProfile savedProfile = repository.save(modelMapper.map(personProfileDto, PersonProfile.class));
        return modelMapper.map(savedProfile, PersonProfileDto.class);
    }

    @Override
    public PersonProfileDto update(PersonProfileDto personProfileDto) {
        return null;
    }

    @Override
    public boolean delete(Integer profileId) {
        return false;
    }

    @Override
    public List<PersonProfileDto> getAll() {
        return null;
    }

    @Override
    public PersonProfileDto getById(Integer id) throws Exception {
        Optional<PersonProfile> optionalPersonProfile = repository.findById(id);
        if (optionalPersonProfile.isEmpty()){
            throw new Exception(String.format("Profile with id %s not found", id));
        }
        return modelMapper.map(optionalPersonProfile.get(),PersonProfileDto.class);
    }
}
