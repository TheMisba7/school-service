package com.mansar.schoolservice.Services;

import com.mansar.schoolservice.Domain.Grade;

import java.util.Collection;

public interface GradeService {

   //TODO : CRUD OPERATIONS
    Grade save(Grade grade);
    Grade getGrade(Long id);
    Grade update(Grade grade);
    void deleteGrade(Long id);
    Collection<Grade> listGrades();
}
