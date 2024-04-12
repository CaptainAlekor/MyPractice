package com.practice.webapi.services;

import com.practice.webapi.models.Group;
import com.practice.webapi.repos.GroupRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class GroupService {
    private GroupRepository groupRepository;

    public Group getGroupById(int id) {
        return groupRepository.findById(id).orElse(null);
    }

    public Group getGroupByStudentId(int studentId) {
        return groupRepository.getGroupByStudentId(studentId).orElse(null);
    }
}
