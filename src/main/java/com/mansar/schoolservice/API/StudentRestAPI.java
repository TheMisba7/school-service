package com.mansar.schoolservice.API;

import com.mansar.schoolservice.Domain.Grade;
import com.mansar.schoolservice.Domain.Student;
import com.mansar.schoolservice.Services.StudentService;
import com.mansar.schoolservice.assemblers.StudentAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(path = "/api/school")
public class StudentRestAPI {
   private StudentService studentService;
   private StudentAssembler studentAssembler;

    public StudentRestAPI(StudentService studentService, StudentAssembler studentAssembler) {
        this.studentService = studentService;
        this.studentAssembler = studentAssembler;
    }

    @GetMapping(path = "/students/{id}")
    public EntityModel<Student> one(@PathVariable(name = "id") Long id)
    {
        return studentAssembler.toModel(studentService.getStudent(id));
    }

    @GetMapping(path = "/students")
    public CollectionModel<EntityModel<Student>> all() {
        List<EntityModel<Student>> list = studentService.listStudents().stream()
                .map(student -> studentAssembler.toModel(student))
                .collect(Collectors.toList());

        return CollectionModel.of(list,
                linkTo(methodOn(StudentRestAPI.class).all()).withSelfRel()
        );
    }
    @PostMapping(path = "/students")
    public ResponseEntity<?> newGrade(@RequestBody Student student) {
        EntityModel<Student> savedEntity =
                studentAssembler.toModel(studentService.save(student));
        return ResponseEntity
                .created(savedEntity.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(savedEntity);
    }
}
