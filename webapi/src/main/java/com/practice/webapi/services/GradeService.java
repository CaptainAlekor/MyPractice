package com.practice.webapi.services;

import com.practice.webapi.models.Grade;
import com.practice.webapi.repos.GradeRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class GradeService {
    private GradeRepository gradeRepository;

    public List<Grade> getGradesByStudentIdAndCourseId(Integer studentId, Integer courseId) {
        return gradeRepository.getGradesByStudentIdAndGroupCourse_CourseId(studentId, courseId);
    }
}
