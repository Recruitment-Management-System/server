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
@IdClass(InterviewInterviewerId.class)
public class InterviewInterviewer {

    @Id
    private int interviewid;

    @Id
    private int interviewerid;

    @ManyToOne
    @JoinColumn(name = "interviewid", insertable = false, updatable = false)
    private Interview interview;

    @ManyToOne
    @JoinColumn(name = "interviewerid", insertable = false, updatable = false)
    private Interviewer interviewer;

    @OneToOne(mappedBy = "interviewInterviewer", cascade = CascadeType.ALL)
    private Feedback feedback;
}
