package com.practice.webapi.controllers;

import com.practice.webapi.models.Group;
import com.practice.webapi.services.GroupService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/groups")
public class GroupController {
    private GroupService groupService;

    @GetMapping
    public ResponseEntity<List<Group>> getAllGroups() {
        return ResponseEntity.ok(groupService.getAllGroups());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Group> getGroupById(@PathVariable(value = "id") int id) {
        return ResponseEntity.ok(groupService.getGroupById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<String> createGroup(@RequestBody String name) {
        Group group = new Group(name);
        return ResponseEntity.ok(groupService.createGroup(group));
    }
}

@Getter
@Setter
@AllArgsConstructor
class CreateGroupData {
    private String name;
}