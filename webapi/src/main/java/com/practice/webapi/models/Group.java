package com.practice.webapi.models;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table
public class Group {
    @Id
    @SequenceGenerator(name = "group_seq",
            sequenceName = "group_seq",
            initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "group_seq")
    private int id;
    @Setter
    @Column(unique = true)
    private String name;
    @JsonIgnore
    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private List<Student> students;
    @JsonManagedReference
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "group_course",
            joinColumns = @JoinColumn(name = "group_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"group_id", "course_id"})})
    private List<Course> courses;

    public Group(String name) {
        this.name = name;
        this.students = new ArrayList<>();
        this.courses = new ArrayList<>();
    }
}
