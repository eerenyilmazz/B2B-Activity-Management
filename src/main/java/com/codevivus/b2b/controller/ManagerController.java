package com.codevivus.b2b.controller;

import com.codevivus.b2b.dtos.ActivityDto;
import com.codevivus.b2b.service.manager.ManagerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/manager")
@AllArgsConstructor
public class ManagerController {

    private final ManagerService managerService;

    @PutMapping("/activity/{activityId}")
    public ResponseEntity<ActivityDto> updateActivity(@PathVariable Long activityId, @RequestBody ActivityDto activityDto) {
        ActivityDto updatedActivity = managerService.updateActivity(activityId, activityDto);
        return ResponseEntity.ok(updatedActivity);
    }
}
