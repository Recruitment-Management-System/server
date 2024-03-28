package com.interviewManagementApplication.RMS.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "interviewinterviewer")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InterviewInterviewer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "interview")
    private Interview interview;

    @ManyToOne
    @JoinColumn(name = "interviewer")
    private Interviewer interviewer;

    @OneToOne(mappedBy = "interviewInterviewer", cascade = CascadeType.ALL)
    private Feedback feedback;
}
