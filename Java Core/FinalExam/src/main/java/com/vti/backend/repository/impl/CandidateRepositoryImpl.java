package com.vti.backend.repository.impl;

import com.vti.backend.repository.ICandidateRepository;
import com.vti.entity.Candidate;

import java.util.ArrayList;
import java.util.List;

public class CandidateRepositoryImpl implements ICandidateRepository {
    private final List<Candidate> store = new ArrayList<>();

    @Override
    public boolean save(Candidate candidate) {
        if (candidate == null) return false;
        return store.add(candidate);
    }

    @Override
    public Candidate findByEmailAndPassword(String email, String password) {
        for (Candidate c : store) {
            if (c.getEmail().equalsIgnoreCase(email) && c.getPassword().equals(password)) {
                return c;
            }
        }
        return null;
    }

    @Override
    public boolean existsByEmail(String email) {
        for (Candidate c : store) {
            if (c.getEmail().equalsIgnoreCase(email)) return true;
        }
        return false;
    }
}
