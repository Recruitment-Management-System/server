package com.interviewManagementApplication.RMS.model;


import com.interviewManagementApplication.RMS.converters.CommentAttributeConverter;

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
    private int feedbackid;

    @Column(name = "details", nullable = false)
    @Convert(converter = CommentAttributeConverter.class)
    private Comment details;

    @Column(name = "overallrating", nullable = false)
    private int overallrating;

    @Column(name = "secondinterview", nullable = false)
    private boolean secondinterview;

    @Column(name = "feedbackdate", nullable = false)
    private Date feedbackdate;

    @ManyToOne
    @JoinColumn(name = "interviewid", nullable = false)
    private Interview interview;

    @Column(name = "userId", nullable = false)
    private int userId;



//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "interviewid", referencedColumnName = "interviewid")
//    private Interview interview;

}
