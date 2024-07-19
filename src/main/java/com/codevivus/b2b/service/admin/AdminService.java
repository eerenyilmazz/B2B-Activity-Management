package com.codevivus.b2b.service.admin;

import com.codevivus.b2b.dtos.ActivityDto;
import com.codevivus.b2b.dtos.ManagerDto;

public interface AdminService {
    ActivityDto createActivity(ActivityDto activityDto);
    ActivityDto updateActivity(Long activityId, ActivityDto activityDto);
    void deleteActivity(Long activityId);
    ManagerDto addManager(Long activityId, ManagerDto managerDto);
    void removeManager(Long activityId, Long managerId);
}
