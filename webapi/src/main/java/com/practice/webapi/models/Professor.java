package com.practice.webapi.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Getter
@NoArgsConstructor
@Entity
@Table
public class Professor extends Person {
    @JsonManagedReference
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "professor_group_course",
            joinColumns = @JoinColumn(name = "professor_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "group_course_id", referencedColumnName = "id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"professor_id", "group_course_id"})})
    private List<GroupCourse> groupCourses;

    public Professor(String name, String surname) {
        super(name, surname);
        this.groupCourses = new ArrayList<>();
    }
    public Professor(String name, String surname, User user) {
        super(name, surname, user);
        this.groupCourses = new ArrayList<>();
    }
}
