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

    @Column(name = "details")
    @Convert(converter = CommentAttributeConverter.class)
    private Comment details;

    @Column(name = "overallrating")
    private int overallrating;

    @Column(name = "secondinterview")
    private boolean secondinterview;

    @Column(name = "feedbackdate")
    private Date feedbackdate;

    @ManyToOne
    @JoinColumn(name = "interviewid")
    private Interview interview;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "interviewid", referencedColumnName = "interviewid")
//    private Interview interview;

}
