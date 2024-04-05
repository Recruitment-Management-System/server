package com.interviewManagementApplication.RMS.repository;

import com.interviewManagementApplication.RMS.model.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VacancyRepository extends JpaRepository<Vacancy, Integer> {
    List<Vacancy> findByProjectProjectID(Integer projectID);

}
