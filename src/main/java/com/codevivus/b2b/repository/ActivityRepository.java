package com.codevivus.b2b.repository;

import com.codevivus.b2b.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long>, JpaSpecificationExecutor<Activity> {
    List<Activity> findByOrderByStartDate();

    List<Activity> findByCategory(String category);

    List<Activity> findByTitleContaining(String keyword);

    boolean existsByIdAndManagerId(Long activityId, Long managerId);

}
