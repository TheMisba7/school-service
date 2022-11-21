package com.mansar.schoolservice.assemblers;

import com.mansar.schoolservice.API.SchoolRestAPI;
import com.mansar.schoolservice.Domain.Manager;
import com.mansar.schoolservice.Domain.User;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@Component
public class UserModelAssembler implements RepresentationModelAssembler<Manager, EntityModel<Manager>> {
    @Override
    public EntityModel<Manager> toModel(Manager entity) {
        return EntityModel.of(entity,
                WebMvcLinkBuilder.linkTo(methodOn(SchoolRestAPI.class).manager(entity.getId())).withSelfRel(),
                linkTo(methodOn(SchoolRestAPI.class).allManagers()).withRel("users")
                );
    }
}
