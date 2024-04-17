package com.practice.webapi.services;

import com.practice.webapi.models.GroupCourse;
import com.practice.webapi.repos.GroupCourseRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class GroupCourseService {
    private GroupCourseRepository groupCourseRepository;

    public GroupCourse getGroupCourseById(int id) {
        return groupCourseRepository.findById(id).orElse(null);
    }
}
