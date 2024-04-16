package com.interviewManagementApplication.RMS.repository;

import com.interviewManagementApplication.RMS.model.Interview;
import com.interviewManagementApplication.RMS.model.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterviewRepo extends JpaRepository<Interview,Integer> {

    List<Interview> findAllByUserList_Id(Integer id);
    List<Interview> findByCandidateCandidateID(Integer candidateid);
}
