package com.mansar.schoolservice.Services;

import com.mansar.schoolservice.Domain.Manager;
import com.mansar.schoolservice.Domain.User;
import com.mansar.schoolservice.Domain.UserRoles;
import com.mansar.schoolservice.Exceptions.NoUserFoundException;
import com.mansar.schoolservice.Repositories.ManagerRepository;

import com.mansar.schoolservice.Repositories.UserRepository;
import com.mansar.schoolservice.Repositories.UserRoleRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.HashSet;

@Service
@Transactional
public class ManagerServiceImpl implements com.mansar.schoolservice.Services.ManagerService {
  private ManagerRepository managerRepository;
   private UserRoleRepository userRoleRepository;
   private UserRepository userRepository;


    public ManagerServiceImpl(ManagerRepository managerRepository, UserRoleRepository userRoleRepository, UserRepository userRepository) {
        this.managerRepository = managerRepository;

        this.userRoleRepository = userRoleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Manager save(Manager user) {
        UserRoles userRoles = userRoleRepository.findById(user.getRole_ID()).get();
        User user_ = new User(null,user.getUsername(),user.getPassword(),new HashSet<>());
        user_.getUserRoles().add(userRoles);
        userRepository.save(user_);
        user.setUser(user_);
        return managerRepository.save(user);
    }

    @Override
    public Manager getUser(Long id) {
        return managerRepository.findById(id).orElseThrow(()->new NoUserFoundException(id));

    }

    @Override
    public Manager update(Manager user) {
        return managerRepository.save(user);

    }

    @Override
    public void deleteUser(Long id) {
        managerRepository.deleteById(id);
    }

    @Override
    public Collection<Manager> listUsers() {
        return managerRepository.findAll();
    }
}
