package com.mansar.schoolservice.Services;

import com.mansar.schoolservice.Domain.Student;

import java.util.Collection;

public interface StudentService {
    //TODO : CRUD OPERATIONS
    Student save(Student student);
    Student getStudent(Long id);
    Student update(Student student);
    void deleteStudent(Long id);
    Collection<Student> listStudents();
}
