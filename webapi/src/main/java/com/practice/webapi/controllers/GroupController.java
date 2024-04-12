package com.practice.webapi.controllers;

import com.practice.webapi.models.Group;
import com.practice.webapi.services.GroupService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/groups")
public class GroupController {
    private GroupService groupService;

    @GetMapping("/{id}")
    public ResponseEntity<Group> getGroupById(@PathVariable(value = "id") int id) {
        return ResponseEntity.ok(groupService.getGroupById(id));
    }
}
