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

    @Column(name = "feedbackdate", nullable = false)
    private Date feedbackdate;

    @Column(name = "salaryexpectation", nullable = false)
    private double salaryexpectation;

    @Column(name = "comment", columnDefinition = "TEXT", nullable = false)
    private String comment;

    @ManyToOne
    @JoinColumn(name = "interviewid", nullable = false)
    private Interview interview;

    @Column(name = "userId", nullable = false)
    private int userId;
}
