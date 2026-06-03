package com.vti.backend.service.impl;

import com.vti.backend.repository.ICandidateRepository;
import com.vti.backend.repository.impl.JdbcCandidateRepositoryImpl;
import com.vti.backend.service.ICandidateService;
import com.vti.entity.Candidate;
import com.vti.entity.ExperienceCandidate;
import com.vti.entity.FresherCandidate;
import com.vti.enums.GraduationRank;
import com.vti.utils.ValidationUtil;

public class CandidateServiceImpl implements ICandidateService {
    private final ICandidateRepository repository = new JdbcCandidateRepositoryImpl();

    @Override
    public boolean register(Candidate candidate) {
        if (candidate == null) return false;
        // common validations
        if (!ValidationUtil.isValidEmail(candidate.getEmail())) return false;
        if (!ValidationUtil.isValidPhone(candidate.getPhone())) return false;
        if (!ValidationUtil.isValidPassword(candidate.getPassword())) return false;
        if (repository.existsByEmail(candidate.getEmail())) return false;

        if (candidate instanceof ExperienceCandidate) {
            ExperienceCandidate e = (ExperienceCandidate) candidate;
            if (!ValidationUtil.isValidExpInYear(e.getExpInYear())) return false;
        } else if (candidate instanceof FresherCandidate) {
            FresherCandidate f = (FresherCandidate) candidate;
            GraduationRank r = f.getGraduationRank();
            if (r == null) return false;
        }

        return repository.save(candidate);
    }

    @Override
    public Candidate login(String email, String password) {
        if (!ValidationUtil.isValidEmail(email)) return null;
        if (!ValidationUtil.isValidPassword(password)) return null;
        return repository.findByEmailAndPassword(email, password);
    }
}
