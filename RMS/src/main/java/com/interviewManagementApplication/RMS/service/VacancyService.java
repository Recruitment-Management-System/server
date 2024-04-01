package com.interviewManagementApplication.RMS.service;

import com.interviewManagementApplication.RMS.model.Vacancy;

import java.util.List;
import java.util.Optional;

public interface VacancyService {

    Vacancy createVacancy(int projectID, Vacancy vacancy);
    void deleteVacancy(Integer vacancyID);
    List<Vacancy> findAll();
    Optional<Vacancy> findByIdVacancy(Integer id);
    Vacancy updateVacancyById(Integer vacancyID, Vacancy vacancy);
}
