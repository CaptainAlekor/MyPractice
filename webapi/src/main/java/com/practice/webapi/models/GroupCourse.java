package com.practice.webapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "group_course")
public class GroupCourse {
    @Id
    @SequenceGenerator(name = "group_course_seq",
            sequenceName = "group_course_seq",
            initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "group_course_seq")
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;
    ///
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "professor_group_course",
            joinColumns = @JoinColumn(name = "group_course_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "professor_id", referencedColumnName = "id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"professor_id", "group_course_id"})})
    private List<Professor> professors;

    public GroupCourse(Group group, Course course) {
        this.group = group;
        this.course = course;
    }
}
