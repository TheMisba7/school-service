package com.mansar.schoolservice;

import com.mansar.schoolservice.Domain.*;
import com.mansar.schoolservice.Domain.Module;
import com.mansar.schoolservice.Repositories.*;
import com.mansar.schoolservice.Services.GradeService;
import com.mansar.schoolservice.Services.ManagerService;
import com.mansar.schoolservice.Services.StudentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.HashSet;

@SpringBootApplication
public class SchoolServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchoolServiceApplication.class, args);
	}


	@Bean
	CommandLineRunner commandLineRunner(SubjectRepository subjectRepository, ManagerService managerService, GradeService gradeRepository, UserRoleRepository userRoleRepository, ModuleRepository moduleRepository, StudentService studentService)
	{
		return args -> {

			subjectRepository.save(new Subject(null,"Licence Info","Computer science",55,1888.87,new ArrayList<>(),new ArrayList<>()));
			userRoleRepository.save(new UserRoles(null,"ADMIN"));
			userRoleRepository.save(new UserRoles(null,"TEACHER"));
			userRoleRepository.save(new UserRoles(null,"USER"));
			userRoleRepository.save(new UserRoles(null,"STUDENT"));

			Manager manager = new  Manager(null,"Ayoub","Chabab","mansar@gmail.com","misba7","password",null,new HashSet<>(),2L);
			managerService.save(manager);


			Subject subject = subjectRepository.findBySubjectName("Licence Info");
			Student student = new Student(null,"studentName1","Student1","student@gmail.com",false,"student","password",null,new Subject(),new HashSet<>());
			student.setSubject(subject);
			studentService.save(student);

			Manager manager_ = new Manager(null,"abdeddaim","Mansar","mansar@gmail.com","misba7","password",null,new HashSet<>(),2L);
			managerService.save(manager_);
			Module module = new Module(null,"Maths",subject.getId(),subject,new HashSet<>(),manager);
			moduleRepository.save(module);
			Grade grade = new Grade((float) 17.4,18,1L,1L,"Great Job");
			gradeRepository.save(grade);



		};
	}
}
