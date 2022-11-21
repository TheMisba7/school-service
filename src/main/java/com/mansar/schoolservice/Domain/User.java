package com.mansar.schoolservice.Domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity @Data
@NoArgsConstructor @AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "users")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<UserRoles> userRoles;

}
