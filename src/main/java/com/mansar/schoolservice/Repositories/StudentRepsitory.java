package com.mansar.schoolservice.Repositories;

import com.mansar.schoolservice.Domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepsitory extends JpaRepository<Student, Long> {
}
