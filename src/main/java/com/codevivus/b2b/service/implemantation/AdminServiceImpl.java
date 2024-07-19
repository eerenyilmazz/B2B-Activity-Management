package com.codevivus.b2b.service.implemantation;

import com.codevivus.b2b.dtos.ActivityDto;
import com.codevivus.b2b.dtos.ManagerDto;
import com.codevivus.b2b.entity.Activity;
import com.codevivus.b2b.entity.Manager;
import com.codevivus.b2b.entity.User;
import com.codevivus.b2b.enums.UserRole;
import com.codevivus.b2b.repository.ActivityRepository;
import com.codevivus.b2b.repository.ManagerRepository;
import com.codevivus.b2b.repository.UserRepository;
import com.codevivus.b2b.service.admin.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;

@Service
@Transactional
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final ActivityRepository activityRepository;
    private final ManagerRepository managerRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public ActivityDto createActivity(ActivityDto activityDto) {
        checkAdminAuthority();
        Activity activity = modelMapper.map(activityDto, Activity.class);
        Activity savedActivity = activityRepository.save(activity);
        return modelMapper.map(savedActivity, ActivityDto.class);
    }

    @Override
    public void deleteActivity(Long activityId) {
        checkAdminAuthority();
        activityRepository.deleteById(activityId);
    }

    @Override
    public ActivityDto updateActivity(Long activityId, ActivityDto activityDto) {
        checkAdminAuthority();
        Activity activity = activityRepository.findById(activityId)
                .orElseThrow(() -> new EntityNotFoundException("Activity not found with id: " + activityId));

        modelMapper.map(activityDto, activity);

        Activity updatedActivity = activityRepository.save(activity);
        return modelMapper.map(updatedActivity, ActivityDto.class);
    }

    @Override
    public ManagerDto addManager(Long activityId, ManagerDto managerDto) {
        checkAdminAuthority();
        Activity activity = activityRepository.findById(activityId)
                .orElseThrow(() -> new EntityNotFoundException("Activity not found with id: " + activityId));

        User user = userRepository.findById(managerDto.getUser().getId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + managerDto.getUser().getId()));

        user.setUserRole(UserRole.MANAGER);
        userRepository.save(user);

        Manager manager = modelMapper.map(managerDto, Manager.class);
        manager.setUser(user);

        activity.setManager(manager);
        managerRepository.save(manager);

        return modelMapper.map(manager, ManagerDto.class);
    }

    @Override
    public void removeManager(Long activityId, Long managerId) {
        checkAdminAuthority();
        Activity activity = activityRepository.findById(activityId)
                .orElseThrow(() -> new EntityNotFoundException("Activity not found with id: " + activityId));

        Manager manager = managerRepository.findById(managerId)
                .orElseThrow(() -> new EntityNotFoundException("Manager not found with id: " + managerId));

        if (activity.getManager() != null && activity.getManager().getId().equals(managerId)) {
            activity.setManager(null);
        }

        managerRepository.delete(manager);
    }

    private void checkAdminAuthority() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User user) {
            if (!UserRole.ADMIN.equals(user.getUserRole())) {
                throw new SecurityException("Only admins are allowed to perform this operation");
            }
        } else {
            throw new SecurityException("Only admins are allowed to perform this operation");
        }
    }
}
