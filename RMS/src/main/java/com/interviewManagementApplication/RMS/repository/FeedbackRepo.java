package com.interviewManagementApplication.RMS.repository;

import com.interviewManagementApplication.RMS.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepo extends JpaRepository<Feedback, Integer> {

    List<Feedback> findByUserid(Integer userid);

    @Query("SELECT f FROM Feedback f WHERE f.interview.interviewid = :interviewid")
    Feedback findFeedbackByInterviewId(@Param("interviewid") int interviewid);

    List<Feedback> findBySecondinterviewTrue();
}
