package com.example.educational.services.impl.impl;

import com.example.educational.dto.UniversityDTO;
import com.example.educational.entities.University;
import com.example.educational.repositories.UniversityRepository;
import com.example.educational.services.impl.UniversityService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UniversityServiceImpl implements UniversityService {
    private final UniversityRepository universityRepository;
    private final ModelMapper modelMapper;

    @Override
    public UniversityDTO saveUniversity(UniversityDTO universityDTO) {
        University university = modelMapper.map(universityDTO, University.class);
        university.setRegistrationNumber("###");
        University savedUniversity = universityRepository.save(university);
        return modelMapper.map(savedUniversity, UniversityDTO.class);
    }

    @Override
    public List<UniversityDTO> getAll() {
        return universityRepository.findAll().stream()
                .map(university -> modelMapper.map(university, UniversityDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public UniversityDTO getById(Long id) {
        return universityRepository.findById(id)
                .map(val ->modelMapper.map(val, UniversityDTO.class))
                .orElse(null);
    }

    @Override
    public UniversityDTO update(UniversityDTO universityDTO){
        University newUniversity = modelMapper.map(universityDTO, University.class);
        Optional<University> optionalUniversity = universityRepository.findById(newUniversity.getId());
        if(optionalUniversity.isPresent()){
            University oldUniversity = optionalUniversity.get();

            newUniversity.setId(oldUniversity.getId());
            newUniversity.setRegistrationNumber(oldUniversity.getRegistrationNumber());

            universityRepository.save(newUniversity);

            return modelMapper.map(newUniversity, UniversityDTO.class);
        }
        return null;
    }

    @Override
    public HttpStatus deleteById(Long id){
        Optional<University> university = universityRepository.findById(id);
        if (university.isPresent()){
            universityRepository.deleteById(id);
            return HttpStatus.OK;
        }
         return HttpStatus.BAD_REQUEST;
    }

}
