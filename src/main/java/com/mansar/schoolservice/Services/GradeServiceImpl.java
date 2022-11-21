package com.mansar.schoolservice.Services;

import com.mansar.schoolservice.Domain.Grade;
import com.mansar.schoolservice.Domain.Module;
import com.mansar.schoolservice.Domain.Student;
import com.mansar.schoolservice.Repositories.GradeRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
@Service
@Transactional
public class GradeServiceImpl implements GradeService {
    private GradeRepository gradeRepository;
    private ModuleService moduleService;

    private StudentService studentService;


    public GradeServiceImpl(GradeRepository gradeRepository, ModuleService moduleService, StudentService studentService) {
        this.gradeRepository = gradeRepository;
        this.moduleService = moduleService;

        this.studentService = studentService;
    }

    @Override
    public Grade save(Grade grade) {
        Module module = moduleService.getModule(grade.getModuleID());
        Student student =  studentService.getStudent(grade.getStudentID());
        grade.setStudent(student);
        grade.setModule(module);


        return gradeRepository.save(grade);
    }

    @Override
    public Grade getGrade(Long id) {
         Grade gr = gradeRepository.findById(id).get();
         Module module = moduleService.getModule(gr.getModuleID());
         Student student = studentService.getStudent(gr.getStudentID());
         gr.setModule(module);
         gr.setStudent(student);
         System.out.println(":::::::::::: "+gr);

        return gr;
    }

    @Override
    public Grade update(Grade grade) {

        return gradeRepository.save(grade);
    }

    @Override
    public void deleteGrade(Long id) {
          gradeRepository.deleteById(id);
    }

    @Override
    public Collection<Grade> listGrades() {

        return gradeRepository.findAll();
    }
}
