package com.example.FirstSpringApplication.repositories;

import com.example.FirstSpringApplication.entities.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniversityRepository extends JpaRepository<University, Long> {
}
