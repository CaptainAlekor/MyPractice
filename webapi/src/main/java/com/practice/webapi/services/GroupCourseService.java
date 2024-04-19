package com.practice.webapi.services;

import com.practice.webapi.models.Course;
import com.practice.webapi.models.Group;
import com.practice.webapi.models.GroupCourse;
import com.practice.webapi.repos.CourseRepository;
import com.practice.webapi.repos.GroupCourseRepository;
import com.practice.webapi.repos.GroupRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin
@Service
@AllArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class GroupCourseService {
    private GroupCourseRepository groupCourseRepository;
    private CourseRepository courseRepository;
    private GroupRepository groupRepository;


    public GroupCourse getGroupCourseById(int id) {
        return groupCourseRepository.findById(id).orElse(null);
    }

    public List<GroupCourse> getAllGroupCourses() {
        return groupCourseRepository.findAll();
    }

    public String createGroupCourse(String groupName, int courseId) {
        Group group = groupRepository.findByName(groupName).orElse(null);
        if (group == null) {
            return "Group not found";
        }
        Course course = courseRepository.findById(courseId).orElse(null);
        if (course == null) {
            return "Course not found";
        }
        try {
            GroupCourse groupCourse = new GroupCourse(group, course);
            groupCourseRepository.save(groupCourse);
            return "Group-course created";
        } catch (Exception e) {
            return "Couldn't create group course: " + e.getMessage();
        }
    }
}
