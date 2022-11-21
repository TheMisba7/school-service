package com.mansar.schoolservice.Domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor @AllArgsConstructor
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String subjectName;
    private String description;
    private int nbOfStudents;
    private double fees;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Transient
    @OneToMany
    Collection<Module> modules;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Transient
    @OneToMany
    Collection<Student> students;

}
