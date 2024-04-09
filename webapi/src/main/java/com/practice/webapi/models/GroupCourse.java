package com.practice.webapi.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    public GroupCourse(Group group, Course course) {
        this.group = group;
        this.course = course;
    }
}
