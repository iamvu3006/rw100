package com.vti.entity;

import com.vti.enums.CandidateRole;

public class ExperienceCandidate extends Candidate {
    private int expInYear;
    private String proSkill;

    public ExperienceCandidate() {
        super();
        setRole(CandidateRole.EXPERIENCE);
    }

    public ExperienceCandidate(String firstName, String lastName, String phone, String email, String password, int expInYear, String proSkill) {
        super(firstName, lastName, phone, email, password, CandidateRole.EXPERIENCE);
        this.expInYear = expInYear;
        this.proSkill = proSkill;
    }

    public int getExpInYear() {
        return expInYear;
    }

    public void setExpInYear(int expInYear) {
        this.expInYear = expInYear;
    }

    public String getProSkill() {
        return proSkill;
    }

    public void setProSkill(String proSkill) {
        this.proSkill = proSkill;
    }
}
