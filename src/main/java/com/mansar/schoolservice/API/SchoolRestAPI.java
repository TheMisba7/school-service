package com.mansar.schoolservice.API;

import com.mansar.schoolservice.Domain.Manager;
import com.mansar.schoolservice.Domain.Subject;
import com.mansar.schoolservice.Exceptions.SubjectNotFoundException;
import com.mansar.schoolservice.Repositories.*;
import com.mansar.schoolservice.Services.ManagerService;
import com.mansar.schoolservice.assemblers.SubjectModelAssembler;
import com.mansar.schoolservice.assemblers.UserModelAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
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
public class SchoolRestAPI {
    private SubjectRepository subjectRepository;

    private ManagerService managerService;

    private SubjectModelAssembler subjectModelAssembler;
    private UserModelAssembler userModelAssembler;


    @Autowired
    public SchoolRestAPI(SubjectRepository subjectRepository, @Lazy ManagerService managerService, SubjectModelAssembler subjectModelAssembler, UserModelAssembler userModelAssembler) {
        this.subjectRepository = subjectRepository;

        this.managerService = managerService;

        this.subjectModelAssembler = subjectModelAssembler;
        this.userModelAssembler = userModelAssembler;
    }


    @GetMapping(path = "/subjects/{id}")
    public EntityModel<Subject> one(@PathVariable(name = "id") Long id) {
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new SubjectNotFoundException(id));

        return subjectModelAssembler.toModel(subject);
    }

    @GetMapping(path = "/subjects")
    public CollectionModel<EntityModel<Subject>> all() {
        List<EntityModel<Subject>> subjects = subjectRepository.findAll().stream()
                .map(subject -> subjectModelAssembler.toModel(subject)).collect(Collectors.toList());
        return CollectionModel.of(subjects,
                linkTo(methodOn(SchoolRestAPI.class)).withSelfRel());
    }

    @PostMapping(path = "/subjects")
    public ResponseEntity<?> newSubject(@RequestBody Subject subject) {
        EntityModel<Subject> savedEntity =
                subjectModelAssembler.toModel(subjectRepository.save(subject));
        return ResponseEntity
                .created(savedEntity.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(savedEntity);
    }

    @GetMapping(path = "/managers")
    public CollectionModel<EntityModel<Manager>> allManagers() {
        List<EntityModel<Manager>> users = managerService.listUsers().stream()
                .map(user -> userModelAssembler.toModel(user))
                .collect(Collectors.toList());
        return CollectionModel.of(users,
                linkTo(methodOn(SchoolRestAPI.class).allManagers()).withSelfRel()
        );

    }

    @GetMapping(path = "/manager/{id}")
    public EntityModel<Manager> manager(@PathVariable(name = "id") Long id) {
        Manager user = managerService.getUser(id);
        return userModelAssembler.toModel(user);

    }
}
