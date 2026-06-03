package com.vti.backend.repository;

import com.vti.entity.Candidate;

public interface ICandidateRepository {
    boolean save(Candidate candidate);
    Candidate findByEmailAndPassword(String email, String password);
    boolean existsByEmail(String email);
}
