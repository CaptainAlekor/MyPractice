package com.practice.webapi.repos;

import com.practice.webapi.models.GroupCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupCourseRepository extends JpaRepository<GroupCourse, Integer> {
    Optional<GroupCourse> findById(int id);
}
