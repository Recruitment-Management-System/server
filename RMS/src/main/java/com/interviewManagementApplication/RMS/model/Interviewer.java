package com.interviewManagementApplication.RMS.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "interviewer")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Interviewer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "interviewerid")
    private int interviewerid;

    @Column(name = "employeeid")
    private String employeeid;

    @Column(name = "interviewerposition")
    private String interviewerposition;

    @Column(name = "infirstname")
    private String firstname;

    @Column(name = "inlastname")
    private String lastname;
}
