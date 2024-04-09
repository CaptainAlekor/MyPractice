package com;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "students")
public class Student extends Person {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Grade> grades;

    public Student() {}
    public Student(String name, String surname) {
        super(name, surname);
        this.grades = new ArrayList<>();
    }
    public Student(String name, String surname, User user) {
        super(name, surname, user);
        this.grades = new ArrayList<>();
    }

    public Group getGroup() {
        return group;
    }
    public void setGroup(Group group) {
        this.group = group;
    }
    public List<Grade> getGrades() {
        return grades;
    }
}
