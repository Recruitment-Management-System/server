package com.interviewManagementApplication.RMS.model;


import com.interviewManagementApplication.RMS.CommentAttributeConverter;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Column(name = "details")
    @Convert(converter = CommentAttributeConverter.class)
    private Comment details;

//    @Column(name = "details")
//    private String details;

    @Column(name = "overallrating")
    private int overallRating;

    @Column(name = "decisiononhire")
    private boolean decisionOnHire;

    @Column(name = "feedbackdate")
    private Date feedbackDate;

    @ManyToOne
    @JoinColumn(name = "interviewid")
    private Interview interview;

    public boolean getDecisionOnHire() {
        return false;
    }
}
