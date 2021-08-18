package com.example.FirstSpringApplication.services.impl.impl;

import com.example.FirstSpringApplication.dto.UniversityDTO;
import com.example.FirstSpringApplication.entities.University;
import com.example.FirstSpringApplication.repositories.UniversityRepository;
import com.example.FirstSpringApplication.services.impl.UniversityService;
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
    public HttpStatus saveUniversity(UniversityDTO universityDTO) {
        University university = modelMapper.map(universityDTO, University.class);
        university.setRegistrationNumber("###");
        University savedUniversity = universityRepository.save(university);
        return savedUniversity != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        //return HttpStatus.OK;
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
        UniversityDTO finalUniversityDTO = null;
        if(optionalUniversity.isPresent()){
            University oldUniversity = optionalUniversity.get();
            oldUniversity.setName(newUniversity.getName());
            oldUniversity.setShortName(newUniversity.getShortName());
            universityRepository.save(oldUniversity);

            finalUniversityDTO = modelMapper.map(oldUniversity, UniversityDTO.class);
            return finalUniversityDTO;
        }
        return finalUniversityDTO;
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
