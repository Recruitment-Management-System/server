package com.interviewManagementApplication.RMS.repository;

import com.interviewManagementApplication.RMS.model.Project;
import com.interviewManagementApplication.RMS.model.User;
import com.interviewManagementApplication.RMS.model.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
    List<Project> findByUsersId(Integer userId);
    @Query("SELECT p, CONCAT(u.firstName, ' ', u.lastName) AS userName FROM Project p JOIN p.users u")
    List<Object[]> findAllProjectsWithUserName();
}
