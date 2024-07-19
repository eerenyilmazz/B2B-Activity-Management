package com.codevivus.b2b.controller;

import com.codevivus.b2b.dtos.ActivityDto;
import com.codevivus.b2b.dtos.ManagerDto;
import com.codevivus.b2b.service.admin.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/activity")
    public ResponseEntity<ActivityDto> createActivity(@RequestBody ActivityDto activityDto) {
        ActivityDto createdActivity = adminService.createActivity(activityDto);
        return ResponseEntity.ok(createdActivity);    }

    @PutMapping("/activity/{activityId}")
    public ResponseEntity<ActivityDto> updateActivity(@PathVariable Long activityId, @RequestBody ActivityDto activityDto) {
        ActivityDto updatedActivity = adminService.updateActivity(activityId, activityDto);
        return ResponseEntity.ok(updatedActivity);
    }

    @DeleteMapping("/activity/{activityId}")
    public ResponseEntity<Void> deleteActivity(@PathVariable Long activityId) {
        adminService.deleteActivity(activityId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/activity/{activityId}/manager")
    public ResponseEntity<ManagerDto> addManagerToActivity(@PathVariable Long activityId, @RequestBody ManagerDto managerDto) {
        ManagerDto addedManager = adminService.addManager(activityId, managerDto);
        return ResponseEntity.ok(addedManager);
    }

    @DeleteMapping("/activity/{activityId}/manager/{managerId}")
    public ResponseEntity<Void> removeManagerFromActivity(@PathVariable Long activityId, @PathVariable Long managerId) {
        adminService.removeManager(activityId, managerId);
        return ResponseEntity.noContent().build();
    }
}