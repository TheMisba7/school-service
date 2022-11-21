package com.mansar.schoolservice.Services;

import com.mansar.schoolservice.Domain.User;

import java.util.Collection;

public interface UserService {
    //TODO : CRUD OPERATIONS
    User save(User user);
    User getUser(Long id);
    User update(User user);
    void deleteUser(Long id);
    Collection<User> listusers();
}
