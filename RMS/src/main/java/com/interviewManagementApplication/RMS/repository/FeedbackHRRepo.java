package com.interviewManagementApplication.RMS.repository;

import com.interviewManagementApplication.RMS.model.Feedback;
import com.interviewManagementApplication.RMS.model.FeedbackHR;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackHRRepo extends JpaRepository<FeedbackHR, Integer> {

    @Query("SELECT f FROM FeedbackHR f WHERE f.interview.interviewid = :interviewid")
    FeedbackHR findFeedbackhrByInterviewId(@Param("interviewid") int interviewid);
}
