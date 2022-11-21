package com.mansar.schoolservice.Repositories;

import com.mansar.schoolservice.Domain.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoles,Long> {
    UserRoles findByName(String name);
}
