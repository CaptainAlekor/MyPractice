package com.practice.webapi.services;

import com.practice.webapi.models.Group;
import com.practice.webapi.repos.GroupRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class GroupService {
    private GroupRepository groupRepository;

    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    public Group getGroupById(int id) {
        return groupRepository.findById(id).orElse(null);
    }

    public Group getGroupByStudentId(int studentId) {
        return groupRepository.getGroupByStudentId(studentId).orElse(null);
    }

    public Group findGroupByName(String name) {
        return groupRepository.findByName(name).orElse(null);
    }

    public String createGroup(Group group) {
        try {
            groupRepository.save(group);
            return "Group created";
        } catch (Exception e) {
            return "Error creating group: " + e.getMessage();
        }
    }
}
