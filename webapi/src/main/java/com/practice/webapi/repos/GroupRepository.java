package com.practice.webapi.repos;

import com.practice.webapi.models.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {
    Optional<Group> findById(int id);
    Optional<Group> findByName(String name);
    @Query("select s.group from Student s where s.id = ?1")
    Optional<Group> getGroupByStudentId(int id);
}
