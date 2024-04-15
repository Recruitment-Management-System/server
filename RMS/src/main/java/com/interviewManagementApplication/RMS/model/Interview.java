package com.interviewManagementApplication.RMS.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

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

    @Enumerated(value = EnumType.STRING)
    @Column(name = "interview_type")
    private InterviewType interviewType;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "interview_status")
    private InterviewStatus interviewStatus = InterviewStatus.PENDING;

    @Column(name = "interview_date")
    private LocalDate interviewDate;

    @Column(name = "interview_time")
    private LocalTime interviewTime;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "candidateid", referencedColumnName = "candidateid")
    private Candidate candidate;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "interviewInterviewer",
            joinColumns = @JoinColumn(name = "interviewid"),
            inverseJoinColumns = @JoinColumn(name = "id")
    )
    private List<User> userList;

    @JsonIgnore
    @OneToMany(mappedBy = "interview")
    private List<Feedback> feedbackList;
}
