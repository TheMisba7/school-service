package com.mansar.schoolservice.Services;

import com.mansar.schoolservice.Domain.Subject;
import com.sun.xml.bind.v2.TODO;

import java.util.Collection;

public interface SubjectService {
    //TODO CRUD OPERATIONS
    Subject save(Subject subject);
    Subject getSubject(Long id);
    Subject update(Subject subject);
    void deleteSubject(Long id);
    Collection<Subject> listSubjects();
}
