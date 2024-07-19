package com.codevivus.b2b.service.implemantation;

import com.codevivus.b2b.entity.Activity;
import com.codevivus.b2b.repository.ActivityRepository;
import com.codevivus.b2b.service.ActivityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class ActivityServiceImpl implements ActivityService {
    private final ActivityRepository activityRepository;

    @Override
    public List<Activity> getActivities() {
        return activityRepository.findByOrderByStartDate();
    }

    @Override
    public List<Activity> getActivitiesByCategory(String category) {
        if(Objects.equals(category, "all")){
            return activityRepository.findByOrderByStartDate();
        }
        return activityRepository.findByCategory(category);
    }

    @Override
    public Activity createActivity(Activity activity) {
        return activityRepository.save(activity);
    }

    @Override
    public List<Activity> searchActivity(String keyword){
        return activityRepository.findByTitleContaining(keyword);
    }

}
