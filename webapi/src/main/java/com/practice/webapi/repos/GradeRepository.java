package com.practice.webapi.repos;

import com.practice.webapi.models.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Integer> {
    Optional<Grade> findById(int id);
    List<Grade> getGradesByStudentIdAndGroupCourse_CourseId(int studentId, int groupCourseCourseId);
    @Modifying
    @Query("update Grade g set g.value = :value where g.id = :id")
    void updateGradeValueById(@Param(value = "id") int id, @Param(value = "value") int value);
    void deleteGradeById(int id);
}
