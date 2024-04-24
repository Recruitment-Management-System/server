package com.interviewManagementApplication.RMS.repository;

import com.interviewManagementApplication.RMS.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepo extends JpaRepository<Feedback, Integer> {

    List<Feedback> findByUserId(Integer userId);

    List<Feedback> findByInterview_Interviewid(int interviewid);

    List<Feedback> findBySecondinterviewTrue();


}
