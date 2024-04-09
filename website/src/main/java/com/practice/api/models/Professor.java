package com;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "professors")
public class Professor extends Person {
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "professor_courses",
            joinColumns = @JoinColumn(name = "professor_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"professor_id", "course_id"})})
    private List<Course> courses;

    public Professor() {}
    public Professor(String name, String surname) {
        super(name, surname);
        this.courses = new ArrayList<>();
    }
    public Professor(String name, String surname, User user) {
        super(name, surname, user);
        this.courses = new ArrayList<>();
    }

    public List<Course> getCourses() {
        return courses;
    }
}
