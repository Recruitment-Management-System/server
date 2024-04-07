package com.interviewManagementApplication.RMS.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "candidate")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "candidateid")
    private int candidateID;

    @Column(name = "nic")
    private String nic;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "experience")
    private int experience;

    @Column(name = "qualification", columnDefinition = "TEXT")
    private String qualification;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Interview> interviews;

    @ManyToMany(mappedBy = "candidateList")
    private List<Vacancy> vacancyList;
}
