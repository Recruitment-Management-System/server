package com.interviewManagementApplication.RMS.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "interview")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Interview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "interviewid")
    private int interviewID;

    @Column(name = "interviewdate")
    private Timestamp interviewdate;

    @ManyToOne
    @JoinColumn(name = "candidateid")
    private Candidate candidate;
}
