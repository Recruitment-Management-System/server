package com.interviewManagementApplication.RMS.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InterviewInterviewerRepo {

    private EntityManager entityManager;

    @Autowired
    public InterviewInterviewerRepo (EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public int countInterviewIdOfInterviewInterviewer(Integer interviewid){
        Query query = entityManager.createNativeQuery("SELECT COUNT(*) FROM interview_interviewer WHERE interviewid = :interviewid");
        query.setParameter("interviewid", interviewid);
        return ((Number)query.getSingleResult()).intValue();
    }

    public List getInterviewerListForAnInterview(Integer interviewid){
        Query query = entityManager.createNativeQuery("SELECT id FROM interview_interviewer WHERE interviewid = :interviewid");
        query.setParameter("interviewid", interviewid);
        return (query.getResultList());
    }
}
