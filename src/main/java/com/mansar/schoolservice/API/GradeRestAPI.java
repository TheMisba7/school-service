package com.mansar.schoolservice.API;

import com.mansar.schoolservice.Domain.Grade;
import com.mansar.schoolservice.Domain.Subject;
import com.mansar.schoolservice.Services.GradeService;
import com.mansar.schoolservice.assemblers.GradeAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(path = "/api/school/")
public class GradeRestAPI {
   private GradeService gradeService;
   private GradeAssembler gradeAssembler;

   @Autowired
    public GradeRestAPI(@Lazy GradeService gradeService, GradeAssembler gradeAssembler) {
        this.gradeService = gradeService;
        this.gradeAssembler = gradeAssembler;
    }

    @GetMapping(path = "/grades/{id}")
    public EntityModel<Grade> one(@PathVariable(name = "id") Long id){
        Grade grade = gradeService.getGrade(id);
        return gradeAssembler.toModel(grade);
    }

    @GetMapping(path = "/grades")
    public CollectionModel<EntityModel<Grade>> all() {
        List<EntityModel<Grade>> grades = gradeService.listGrades().stream()
                .map(grade -> gradeAssembler.toModel(grade))
                .collect(Collectors.toList());
        return CollectionModel.of(grades,
                linkTo(methodOn(GradeRestAPI.class).all()).withSelfRel()
                );
    }
    @PostMapping(path = "/grades")
    public ResponseEntity<?> newGrade(@RequestBody Grade grade) {
        EntityModel<Grade> savedEntity =
                gradeAssembler.toModel(gradeService.save(grade));
        return ResponseEntity
                .created(savedEntity.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(savedEntity);
    }
}
