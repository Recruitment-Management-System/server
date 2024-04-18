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

    @Column(name = "reason" , nullable = false)
    private String reason;

    @Column(name = "jobrefcode", nullable = false)
    private String jobRefCode;

    @Column(name = "jobrole" , nullable = false)
    private String jobRole;

    @Column(nullable = false)
    private int openings;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private VacancyStatusType status;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "projectID", referencedColumnName = "projectId", nullable = false)
    private Project project;

    @ManyToMany(mappedBy = "vacancyList")
    private List<Candidate> candidateList;
}
