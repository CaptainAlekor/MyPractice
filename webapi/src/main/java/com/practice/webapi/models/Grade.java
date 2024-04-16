package com.practice.webapi.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Getter
@NoArgsConstructor
@Entity
@Table
public class Grade {
    @Id
    @SequenceGenerator(name = "grade_seq",
            sequenceName = "grade_seq",
            initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "grade_seq")
    private int id;
    @Setter
    private int value;
    @Setter
    @Basic
    private Date date;
    @Setter
    @JsonManagedReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id")
    private Student student;
    @JsonIgnore
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_course_id")
    private GroupCourse groupCourse;

    public Grade(int value) {
        this.value = value;
        this.date = new Date(System.currentTimeMillis());
    }
    public Grade(int value, String date) {
        this.value = value;
        try {
            this.date = parseDate(date);
        } catch (ParseException e) {
            this.date = new Date(System.currentTimeMillis());
            System.out.println(DATE_PARSE_EXCEPTION_MESSAGE);
        }

    }
    public Grade(int value, Student student, GroupCourse groupCourse) {
        this.value = value;
        this.date = new Date(System.currentTimeMillis());
        this.student = student;
        this.groupCourse = groupCourse;
        student.getGrades().add(this);
    }
    public Grade(int value, String date, Student student, GroupCourse groupCourse) {
        this.value = value;
        try {
            this.date = parseDate(date);
        } catch (ParseException e) {
            this.date = new Date(System.currentTimeMillis());
            System.out.println(DATE_PARSE_EXCEPTION_MESSAGE);
        }
        this.student = student;
        this.groupCourse = groupCourse;
        student.getGrades().add(this);
    }

    public void setDateByString(String date) {
        try {
            this.date = parseDate(date);
        } catch (ParseException e) {
            this.date = new Date(System.currentTimeMillis());
            System.out.println(DATE_PARSE_EXCEPTION_MESSAGE);
        }
    }

    public static Date parseDate(String date) throws ParseException {
        String formatStr = "yyyy-MM-dd";
        return parseDate(date, formatStr);
    }
    public static Date parseDate(String date, String formatStr) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        java.util.Date utilDate = format.parse(date);
        return new Date(utilDate.getTime());
    }

    private static final String DATE_PARSE_EXCEPTION_MESSAGE = "Date parse exception. Current date has been set instead.";
}
