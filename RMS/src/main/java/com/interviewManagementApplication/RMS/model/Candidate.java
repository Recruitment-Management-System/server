package com.interviewManagementApplication.RMS.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

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

    @Column(name = "status")
    private String status;

    @Column(name = "cv")
    private String cv;

    @Column(name = "cvpath")
    private byte[] cvpath;


    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Interview> interviews;


   @JsonIgnore
   @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
   @JoinTable(
           name = "candidateVacancy",
           joinColumns = @JoinColumn(name = "candidateid"),
           inverseJoinColumns = @JoinColumn(name = "vacancyid")
   )
    private List<Vacancy> vacancyList;
}
