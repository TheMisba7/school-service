package com.mansar.schoolservice.Domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity @Data @AllArgsConstructor @NoArgsConstructor
@DiscriminatorValue("student")
public class Student {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private boolean isPaid;
    @JsonIgnore
    @Transient
    private String username;
    @JsonIgnore
    @Transient
    private String password;
    @OneToOne
    private User user;
    @ManyToOne(fetch = FetchType.EAGER)
    Subject subject;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Transient
    @OneToMany
    Set<Grade> grade;


}
