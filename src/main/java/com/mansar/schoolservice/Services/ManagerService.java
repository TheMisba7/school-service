package com.mansar.schoolservice.Services;

import com.mansar.schoolservice.Domain.Manager;

import java.util.Collection;

public interface ManagerService {
    //TODO : CRUD OPERATIONS!!
    Manager save(Manager User);
    Manager getUser(Long id);
    Manager update(Manager user);
    void deleteUser(Long id);
    Collection<Manager> listUsers();
}
