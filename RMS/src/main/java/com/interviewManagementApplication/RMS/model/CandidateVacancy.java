package com.interviewManagementApplication.RMS.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "candidatevacancy")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CandidateVacancy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "candidateid")
    private Candidate candidate;

    @ManyToOne
    @JoinColumn(name = "vacancyid")
    private Vacancy vacancy;
}
