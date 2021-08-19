package com.example.educational.services.impl.impl;

import com.example.educational.dto.FacultyDTO;
import com.example.educational.entities.Faculty;
import com.example.educational.entities.University;
import com.example.educational.repositories.FacultyRepository;
import com.example.educational.services.impl.FacultyService;
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
    private final UniversityServiceImpl universityService;
    private final FacultyRepository facultyRepository;

    @Override
    public HttpStatus save(FacultyDTO facultyDTO) {
        Faculty faculty = modelMapper.map(facultyDTO, Faculty.class);
        faculty.setUniversity(modelMapper.map(universityService.getById(facultyDTO.getUniversityId()), University.class));
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
        if(optionalFaculty.isPresent()){
            Faculty oldFaculty = optionalFaculty.get();

            newFaculty.setId(oldFaculty.getId());
            newFaculty.setUniversity(oldFaculty.getUniversity());

            facultyRepository.save(newFaculty);

            return modelMapper.map(newFaculty, FacultyDTO.class);
        }
        return null;
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
