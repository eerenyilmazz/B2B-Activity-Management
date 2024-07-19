package com.codevivus.b2b.service.manager;

import com.codevivus.b2b.dtos.ActivityDto;

public interface ManagerService {
    ActivityDto updateActivity(Long activityId, ActivityDto activityDto);
}
