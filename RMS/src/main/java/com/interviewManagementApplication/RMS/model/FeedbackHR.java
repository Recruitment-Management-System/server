package com.interviewManagementApplication.RMS.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "feedbackhr")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FeedbackHR {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedbackidhr")
    private int feedbackidhr;

    @Column(name = "feedbackdate")
    private Date feedbackdate;

    @Column(name = "salaryexpectation")
    private double salaryexpectation;

    @Column(name = "comment", columnDefinition = "TEXT")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "interviewid")
    private Interview interview;

    @Column(name = "userId")
    private int userId;
}
