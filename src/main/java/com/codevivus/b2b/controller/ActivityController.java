package com.codevivus.b2b.controller;

import com.codevivus.b2b.entity.Activity;
import com.codevivus.b2b.service.ActivityService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/activity")
@EnableAutoConfiguration
@CrossOrigin
@AllArgsConstructor
public class ActivityController {

    private final ActivityService activityService;

    @GetMapping
    public ResponseEntity<List<Activity>> getAllActivity() throws IOException {
        List<Activity> activities = activityService.getActivities();
        return ResponseEntity.ok(activities);
    }

    @PostMapping()
    public ResponseEntity<Activity> createActivity(@RequestBody Activity activity) throws IOException {
        return ResponseEntity.ok(activityService.createActivity(activity));
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Activity>> filterActivity(@RequestParam String category) throws IOException {
        List<Activity> activities = activityService.getActivitiesByCategory(category);
        return ResponseEntity.ok(activities);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Activity>> searchActivity(@RequestParam String keyword) throws IOException {
        List<Activity> activities = activityService.searchActivity(keyword);
        return ResponseEntity.ok(activities);
    }
}
