package com.example.educational.repositories;

import com.example.educational.entities.PersonProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonProfileRepository extends JpaRepository<PersonProfile, Integer> {
}
