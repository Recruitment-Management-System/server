package com.interviewManagementApplication.RMS.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vacancy")
public class Vacancy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vacancyid")
    private Integer vacancyID;

    @Column(name = "reason")
    private String reason;

    @Column(name = "jobrefcode")
    private String jobRefCode;

    @Column(name = "jobrole")
    private String jobRole;

    @Column
    private int openings;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private VacancyStatusType status = VacancyStatusType.OPEN;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "projectID", referencedColumnName = "projectId")
    private Project project;

    @ManyToMany(mappedBy = "vacancyList")
    private List<Candidate> candidateList;
}
