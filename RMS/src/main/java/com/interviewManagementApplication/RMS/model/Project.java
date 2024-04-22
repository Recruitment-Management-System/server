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
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "projectid")
    private int projectID;

    @Column(name = "projectname", nullable = false)
    private String projectName;

    @Column(name = "projectcode", nullable = false)
    private String projectCode;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Vacancy> vacancyList;


    @ManyToOne
    @JoinColumn(name = "id", nullable = false)
    private User users;
}
