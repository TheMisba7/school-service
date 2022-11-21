package com.mansar.schoolservice.Repositories;

import com.mansar.schoolservice.Domain.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<Manager,Long> {
}
