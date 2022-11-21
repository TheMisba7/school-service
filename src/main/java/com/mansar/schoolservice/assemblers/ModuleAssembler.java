package com.mansar.schoolservice.assemblers;

import com.mansar.schoolservice.API.ModuleRestAPI;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import com.mansar.schoolservice.Domain.Module;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@Component
public class ModuleAssembler implements RepresentationModelAssembler<Module, EntityModel<Module>> {
    @Override
    public EntityModel<Module> toModel(Module entity) {
        return EntityModel.of(entity,
                WebMvcLinkBuilder.linkTo(methodOn(ModuleRestAPI.class).one(entity.getSubjectID(),entity.getId())).withSelfRel(),
                linkTo(methodOn(ModuleRestAPI.class).all(entity.getId())).withRel("modules")
                );
    }
}
