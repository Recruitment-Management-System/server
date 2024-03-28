package com.interviewManagementApplication.RMS.repository;

import com.interviewManagementApplication.RMS.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepo extends JpaRepository<Feedback, Integer> {
}
