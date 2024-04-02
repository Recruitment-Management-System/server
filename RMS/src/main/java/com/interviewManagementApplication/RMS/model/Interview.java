package com.interviewManagementApplication.RMS.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Integer interviewID;

    @Column(name = "interview_type")
    private int interviewType;

    @Column(name = "interview_status")
    private int interviewStatus;

    @Column(name = "interviewdate")
    private Timestamp interviewdate;

    @Column(name = "interview_time")
    private Timestamp interviewTime;

    @ManyToOne
    @JoinColumn(name = "candidateid")
    private Candidate candidate;
}
