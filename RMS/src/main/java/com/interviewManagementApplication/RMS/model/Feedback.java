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
    @Column(name = "feedbackid")
    private int feedback_ID;

    @OneToOne
    @JoinColumn(name = "interviewid")
    @JoinColumn(name = "interviewerid")
    private InterviewInterviewer interviewInterviewer;

    @Column(name = "details")
    private String comment;

    @Column(name = "overallrating")
    private int overallRating;

    @Column(name = "decisiononhire")
    private boolean decisionOnHire;

    @Column(name = "feedbackdate")
    private Date feedbackDate;
}
