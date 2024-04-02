package com.interviewManagementApplication.RMS.repository;

import com.interviewManagementApplication.RMS.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepo extends JpaRepository<Candidate, Integer> {
}
