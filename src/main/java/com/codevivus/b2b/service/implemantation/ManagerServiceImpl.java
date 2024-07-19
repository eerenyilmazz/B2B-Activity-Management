package com.codevivus.b2b.service.implemantation;

import com.codevivus.b2b.dtos.ActivityDto;
import com.codevivus.b2b.entity.Activity;
import com.codevivus.b2b.entity.Manager;
import com.codevivus.b2b.entity.User;
import com.codevivus.b2b.repository.ActivityRepository;
import com.codevivus.b2b.repository.ManagerRepository;
import com.codevivus.b2b.repository.UserRepository;
import com.codevivus.b2b.service.manager.ManagerService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@Transactional
@AllArgsConstructor
public class ManagerServiceImpl implements ManagerService {

    private final ActivityRepository activityRepository;
    private final ManagerRepository managerRepository;
    private final UserRepository userRepository;

    @Override
    public ActivityDto updateActivity(Long activityId, ActivityDto activityDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserEmail = authentication.getName();
        User currentUser = userRepository.findFirstByEmail(currentUserEmail)
                .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + currentUserEmail));

        Manager manager = managerRepository.findByUserId(currentUser.getId())
                .orElseThrow(() -> new EntityNotFoundException("Manager not found for user with id: " + currentUser.getId()));

        if (!manager.isCanManageEvents()) {
            throw new SecurityException("User is not authorized to manage events");
        }

        Activity activity = activityRepository.findById(activityId)
                .orElseThrow(() -> new EntityNotFoundException("Activity not found with id: " + activityId));

        mapDtoToActivity(activityDto, activity);

        Activity updatedActivity = activityRepository.save(activity);
        return mapActivityToDto(updatedActivity);
    }

    private ActivityDto mapActivityToDto(Activity activity) {
        ActivityDto activityDto = new ActivityDto();
        activityDto.setTitle(activity.getTitle());
        activityDto.setDescription(activity.getDescription());
        activityDto.setStartDate(activity.getStartDate());
        activityDto.setEndDate(activity.getEndDate());
        activityDto.setCategory(activity.getCategory());
        activityDto.setLocation(activity.getLocation());
        activityDto.setImage(activity.getImage());
        activityDto.setUrl(activity.getUrl());
        return activityDto;
    }

    private void mapDtoToActivity(ActivityDto activityDto, Activity activity) {
        activity.setTitle(activityDto.getTitle());
        activity.setDescription(activityDto.getDescription());
        activity.setStartDate(activityDto.getStartDate());
        activity.setEndDate(activityDto.getEndDate());
        activity.setCategory(activityDto.getCategory());
        activity.setLocation(activityDto.getLocation());
        activity.setImage(activityDto.getImage());
        activity.setUrl(activityDto.getUrl());
    }
}
