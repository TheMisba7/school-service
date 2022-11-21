package com.mansar.schoolservice.assemblers;

import com.mansar.schoolservice.API.StudentRestAPI;
import com.mansar.schoolservice.Domain.Student;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class StudentAssembler implements RepresentationModelAssembler<Student,EntityModel<Student>> {

    @Override
    public EntityModel<Student> toModel(Student entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(StudentRestAPI.class).one(entity.getId())).withSelfRel(),
                linkTo(methodOn(StudentRestAPI.class).all()).withRel("students")
                );
    }
}
