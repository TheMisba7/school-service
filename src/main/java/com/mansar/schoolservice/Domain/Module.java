package com.mansar.schoolservice.Domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.Set;

//a subject in school has many modules
@Entity
@Data
@NoArgsConstructor @AllArgsConstructor
public class Module {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  id;
    private String moduleName;
    private Long subjectID;
    @Transient
    @ManyToOne
    Subject subject;
    @Transient
    @OneToMany
    private Set<Grade> grades;
    @OneToOne
    Manager manager;

    public Module(Long id, String moduleName, Long subject_ID) {
        this.id = id;
        this.moduleName = moduleName;
        this.subjectID = subject_ID;
    }

}
