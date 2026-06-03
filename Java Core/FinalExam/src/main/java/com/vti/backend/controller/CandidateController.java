package com.vti.backend.controller;

import com.vti.backend.service.ICandidateService;
import com.vti.backend.service.impl.CandidateServiceImpl;
import com.vti.entity.Candidate;

public class CandidateController {
    private final ICandidateService service = new CandidateServiceImpl();

    public boolean register(Candidate candidate) {
        return service.register(candidate);
    }

    public Candidate login(String email, String password) {
        return service.login(email, password);
    }
}
