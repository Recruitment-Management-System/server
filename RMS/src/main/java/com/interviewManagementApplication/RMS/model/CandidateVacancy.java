package com.interviewManagementApplication.RMS.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "candidatevacancy")
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(CandidateVacancyId.class)
public class CandidateVacancy {

    @Id
    private int candidateid;

    @Id
    private int vacancyid;

    @ManyToOne
    @JoinColumn(name = "candidateid", insertable = false, updatable = false)
    private Candidate candidate;

    @ManyToOne
    @JoinColumn(name = "vacancyid", insertable = false, updatable = false)
    private Vacancy vacancy;
}
