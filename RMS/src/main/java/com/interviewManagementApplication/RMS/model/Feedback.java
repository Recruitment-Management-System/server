package com.interviewManagementApplication.RMS.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "feedback")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedback_ID")
    private int feedback_ID;

    @Column(name = "interview_ID")
    private int interviewID;

    @Column(name = "interviewer_ID")
    private int interviewer_ID;

    @Column(name = "feedback_comment")
    private String comment;

    @Column(name = "overallRating")
    private int overallRating;

    @Column(name = "decisionOnHire")
    private boolean decisionOnHire;

    @Column(name = "feedbackDate")
    private Date feedbackDate;
}
