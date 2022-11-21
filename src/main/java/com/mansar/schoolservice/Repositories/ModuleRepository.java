package com.mansar.schoolservice.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mansar.schoolservice.Domain.Module;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ModuleRepository extends JpaRepository<Module,Long> {
    Collection<Module> findBySubjectID(Long id);
    Module findBySubjectIDAndId(Long subjectID, Long id);
}
