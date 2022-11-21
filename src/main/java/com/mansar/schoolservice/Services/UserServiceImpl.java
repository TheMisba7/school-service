package com.mansar.schoolservice.Services;

import com.mansar.schoolservice.Domain.User;
import com.mansar.schoolservice.Repositories.UserRepository;

import java.util.Collection;

public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUser(Long id) {

        return userRepository.findById(id).get();
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public void deleteUser(Long id) {

    }


    @Override
    public Collection<User> listusers() {
        return userRepository.findAll();
    }
}
