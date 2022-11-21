package com.mansar.schoolservice.API;

import com.mansar.schoolservice.Services.ModuleService;
import com.mansar.schoolservice.assemblers.ModuleAssembler;
import org.springframework.context.annotation.Lazy;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.mansar.schoolservice.Domain.Module;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(path = "/api/school/subject")
public class ModuleRestAPI {
    private ModuleService moduleService;
    private ModuleAssembler moduleAssembler;

    public ModuleRestAPI(@Lazy ModuleService moduleService, ModuleAssembler moduleAssembler) {
        this.moduleService = moduleService;
        this.moduleAssembler = moduleAssembler;
    }
    @GetMapping(path = "/{subjectID}/modules/{id}")
    public EntityModel<Module> one(@PathVariable(name = "subjectID") Long subjectID,
            @PathVariable(name = "id") Long id)
    {
        return moduleAssembler.toModel(moduleService.getModuleBySubject(subjectID,id));
    }
    @GetMapping(path = "/{id}/modules")
    public CollectionModel<EntityModel<Module>> all(@PathVariable(name = "id")Long id)
    {
        List<EntityModel<Module>> models = moduleService.listModule(id).stream()
                .map(module -> moduleAssembler.toModel(module))
                .collect(Collectors.toList());

        return CollectionModel.of(models,
                linkTo(methodOn(ModuleRestAPI.class).all(id)).withSelfRel()
                );

    }
    @PostMapping(path = "/modules")
    public ResponseEntity<?> newModule(@RequestBody Module module) {
        EntityModel<Module> savedEntity =
                moduleAssembler.toModel(moduleService.save(module));
        return ResponseEntity
                .created(savedEntity.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(savedEntity);
    }
    @PostMapping(path = "/module")
    public ResponseEntity<?> teacherToModule(@RequestParam(name = "teacherID") int teacherID,
                                             @RequestParam(name = "moduleID") int moduleID) {
        EntityModel<Module> updatedEntity =
                moduleAssembler.toModel(moduleService.addTeacher(Long.valueOf(teacherID),Long.valueOf(moduleID)));
        return ResponseEntity
                .created(updatedEntity.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(updatedEntity);
    }
}
