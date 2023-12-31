package com.rentSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rentSystem.model.Problem;

@Repository
public interface ProblemRepository extends JpaRepository<Problem, Long> {
    
}
