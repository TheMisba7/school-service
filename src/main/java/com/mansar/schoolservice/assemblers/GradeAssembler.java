package com.mansar.schoolservice.assemblers;

import com.mansar.schoolservice.API.GradeRestAPI;
import com.mansar.schoolservice.API.ModuleRestAPI;
import com.mansar.schoolservice.Domain.Grade;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@Component
public class GradeAssembler implements RepresentationModelAssembler<Grade, EntityModel<Grade>> {
    @Override
    public EntityModel<Grade> toModel(Grade entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(GradeRestAPI.class).one(entity.getId())).withSelfRel(),
                linkTo(methodOn(GradeRestAPI.class).all()).withRel("grades")
                );
    }
}
