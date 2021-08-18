package com.example.FirstSpringApplication.services.impl.impl;

import com.example.FirstSpringApplication.dto.FacultyDTO;
import com.example.FirstSpringApplication.entities.Faculty;
import com.example.FirstSpringApplication.repositories.FacultyRepository;
import com.example.FirstSpringApplication.repositories.UniversityRepository;
import com.example.FirstSpringApplication.services.impl.FacultyService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FacultyServiceImpl implements FacultyService {
    private final ModelMapper modelMapper;
    private final FacultyRepository facultyRepository;
    private final UniversityRepository universityRepository;

    @Override
    public HttpStatus save(FacultyDTO facultyDTO) {
        Faculty faculty = modelMapper.map(facultyDTO, Faculty.class);
        faculty.setUniversity(universityRepository.getById(facultyDTO.getUniversityId()));
        Faculty savedFaculty = facultyRepository.save(faculty);
        return savedFaculty==null?HttpStatus.BAD_REQUEST:HttpStatus.OK;
    }

    @Override
    public List<FacultyDTO> getAll() {
        return facultyRepository.findAll().stream()
                .map(faculty -> modelMapper.map(faculty, FacultyDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public FacultyDTO getById(Long id) {
        return facultyRepository.findById(id)
                .map(faculty -> modelMapper.map(faculty, FacultyDTO.class))
                .orElse(null);
    }

    @Override
    public FacultyDTO update(FacultyDTO facultyDTO) {
        Faculty newFaculty = modelMapper.map(facultyDTO, Faculty.class);
        Optional<Faculty> optionalFaculty = facultyRepository.findById(newFaculty.getId());
        FacultyDTO  oldFacultyDto = null;
        if(optionalFaculty.isPresent()){
            Faculty oldFaculty = optionalFaculty.get();

            oldFaculty.setName(newFaculty.getName());
            oldFaculty.setShortName(newFaculty.getShortName());
            oldFaculty.setAddress(newFaculty.getAddress());

            facultyRepository.save(oldFaculty);
            oldFacultyDto = modelMapper.map(oldFaculty, FacultyDTO.class);
            return oldFacultyDto;
        }
        return oldFacultyDto;
    }

    @Override
    public HttpStatus deleteById(Long id) {
        Optional<Faculty> oldFacultyOpt = facultyRepository.findById(id);
        if(oldFacultyOpt.isPresent()){
            facultyRepository.deleteById(id);
            return HttpStatus.OK;
        }
        return HttpStatus.BAD_REQUEST;
    }
}
