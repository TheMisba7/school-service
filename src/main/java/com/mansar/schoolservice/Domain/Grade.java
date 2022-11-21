package com.mansar.schoolservice.Domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
public class Grade {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private float Exam1;
    private float Exam2;
    private Long studentID;
    private Long moduleID;
    private String note;
    @Transient
    @ManyToOne
    Module module;
    @Transient
    @ManyToOne
    Student student;

    public Grade(float exam1, float exam2, Long studentID, Long moduleID, String note) {
        Exam1 = exam1;
        Exam2 = exam2;
        this.studentID = studentID;
        this.moduleID = moduleID;
        this.note = note;
    }
}
