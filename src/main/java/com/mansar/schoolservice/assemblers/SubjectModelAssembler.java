package com.mansar.schoolservice.assemblers;

import com.mansar.schoolservice.API.ModuleRestAPI;
import com.mansar.schoolservice.API.SchoolRestAPI;
import com.mansar.schoolservice.Domain.Subject;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class SubjectModelAssembler implements RepresentationModelAssembler<Subject, EntityModel<Subject>>{
    @Override
    public EntityModel<Subject> toModel(Subject entity) {
        return EntityModel.of(entity,
                WebMvcLinkBuilder.linkTo(methodOn(SchoolRestAPI.class).one(entity.getId())).withSelfRel(),
                linkTo(methodOn(SchoolRestAPI.class).all()).withRel("subjects"),
                linkTo(methodOn(ModuleRestAPI.class).all(entity.getId())).withRel(entity.getId()+"/modules")
                );
    }
}
