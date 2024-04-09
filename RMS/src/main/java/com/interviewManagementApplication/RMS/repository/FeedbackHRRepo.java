package com.interviewManagementApplication.RMS.repository;

import com.interviewManagementApplication.RMS.model.FeedbackHR;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackHRRepo extends JpaRepository<FeedbackHR, Integer> {
}
