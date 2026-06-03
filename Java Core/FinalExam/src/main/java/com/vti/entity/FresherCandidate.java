package com.vti.entity;

import com.vti.enums.CandidateRole;
import com.vti.enums.GraduationRank;

public class FresherCandidate extends Candidate {
    private GraduationRank graduationRank;

    public FresherCandidate() {
        super();
        setRole(CandidateRole.FRESHER);
    }

    public FresherCandidate(String firstName, String lastName, String phone, String email, String password, GraduationRank graduationRank) {
        super(firstName, lastName, phone, email, password, CandidateRole.FRESHER);
        this.graduationRank = graduationRank;
    }

    public GraduationRank getGraduationRank() {
        return graduationRank;
    }

    public void setGraduationRank(GraduationRank graduationRank) {
        this.graduationRank = graduationRank;
    }
}
