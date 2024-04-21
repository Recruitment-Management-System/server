package com.interviewManagementApplication.RMS.repository;

import com.interviewManagementApplication.RMS.model.Interview;
import com.interviewManagementApplication.RMS.model.InterviewStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterviewRepo extends JpaRepository<Interview,Integer> {

    List<Interview> findAllByUserList_Id(Integer id);
    List<Interview> findByCandidateCandidateID(Integer candidateid);

    //if (interviewInterviewer) interviewId count == (feedback) interviewId count
    @Query("SELECT COUNT(f.feedbackid) FROM Feedback f WHERE f.interview.interviewid = :interviewid")
    int countFeedbacks(Integer interviewid);

    @Modifying
    @Query("UPDATE Interview i SET i.interviewStatus=:interviewStatus WHERE i.interviewid = :interviewid")
    void updateInterviewStatus(Integer interviewid, InterviewStatus interviewStatus);
}
