package com.codevivus.b2b.service;

import com.codevivus.b2b.entity.Activity;

import java.util.Date;
import java.util.List;

public interface ActivityService {

    List<Activity> getActivities();

    List<Activity> getActivitiesByCategory(String category);

    Activity createActivity(Activity activity);

    List<Activity> searchActivity(String keyword);
}
