package com.interviewManagementApplication.RMS.repository;

import com.interviewManagementApplication.RMS.model.Interview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterviewRepo extends JpaRepository<Interview,Integer> {
}
