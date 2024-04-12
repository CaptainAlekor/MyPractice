package com.practice.webapi.repos;

import com.practice.webapi.models.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Integer> {
    Optional<Grade> findById(int id);
    List<Grade> getGradesByStudentIdAndGroupCourse_CourseId(int studentId, int groupCourseCourseId);
}
