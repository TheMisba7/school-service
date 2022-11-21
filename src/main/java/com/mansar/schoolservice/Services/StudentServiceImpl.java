package com.mansar.schoolservice.Services;

import com.mansar.schoolservice.Domain.Student;
import com.mansar.schoolservice.Domain.Subject;
import com.mansar.schoolservice.Domain.User;
import com.mansar.schoolservice.Domain.UserRoles;
import com.mansar.schoolservice.Repositories.StudentRepsitory;
import com.mansar.schoolservice.Repositories.UserRepository;
import com.mansar.schoolservice.Repositories.UserRoleRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.HashSet;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {
    private StudentRepsitory studentRepsitory;
    private UserRoleRepository userRoleRepository;
    private UserRepository userRepository;
    private SubjectService subjectService;
    public StudentServiceImpl(StudentRepsitory studentRepsitory, UserRoleRepository userRoleRepository, UserRepository userRepository, SubjectService subjectService) {
        this.studentRepsitory = studentRepsitory;
        this.userRoleRepository = userRoleRepository;
        this.userRepository = userRepository;
        this.subjectService = subjectService;
    }

    @Override
    public Student save(Student student) {
        UserRoles userRoles = userRoleRepository.findByName("STUDENT");
        User user = new User(null,student.getUsername(),student.getPassword(),new HashSet<>());
        user.getUserRoles().add(userRoles);
        userRepository.save(user);
        Subject subject = subjectService.getSubject(1L);
        student.setUser(user);
        student.setSubject(subject);
        return studentRepsitory.save(student);
    }

    @Override
    public Student getStudent(Long id) {

        return studentRepsitory.findById(id).get();
    }

    @Override
    public Student update(Student student) {
        return null;
    }

    @Override
    public void deleteStudent(Long id) {

    }

    @Override
    public Collection<Student> listStudents() {
        return studentRepsitory.findAll();
    }
}
