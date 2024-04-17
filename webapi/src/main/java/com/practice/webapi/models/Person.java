package com.practice.webapi.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "surname"})})
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Person {
    @Id
    @SequenceGenerator(name = "person_seq",
            sequenceName = "person_seq",
            initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "person_seq")
    protected int id;
    @Setter
    protected String name;
    @Setter
    protected String surname;
    @Setter
    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", unique = true)
    protected User user;

    protected Person(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
    protected Person(String name, String surname, User user) {
        this.name = name;
        this.surname = surname;
        this.user = user;
        user.setPerson(this);
    }
}
