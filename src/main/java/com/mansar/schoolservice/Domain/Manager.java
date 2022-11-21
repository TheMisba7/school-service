package com.mansar.schoolservice.Domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity @Data @AllArgsConstructor @NoArgsConstructor
public class Manager{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    @JsonIgnore
    @Transient
    private String username;
    @JsonIgnore
    @Transient
    private String password;
    @OneToOne
    private User user;
    @Transient
    @OneToMany(fetch = FetchType.EAGER)
    Set<Module> module;
    @Transient
    private Long role_ID;


}
