package com.practice.webapi.services;

import com.practice.webapi.models.Course;
import com.practice.webapi.repos.CourseRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class CourseService {
    private CourseRepository courseRepository;

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public String createCourse(Course course) {
        try {
            courseRepository.save(course);
            return "Course created";
        } catch (Exception e) {
            return "Course not created: " + e.getMessage();
        }
    }
}
