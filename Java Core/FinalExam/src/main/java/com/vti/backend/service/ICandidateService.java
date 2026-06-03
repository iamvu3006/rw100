package com.vti.backend.service;

import com.vti.entity.Candidate;

public interface ICandidateService {
    boolean register(Candidate candidate);
    Candidate login(String email, String password);
}
