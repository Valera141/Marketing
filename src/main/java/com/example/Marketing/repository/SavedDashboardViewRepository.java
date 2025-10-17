package com.example.Marketing.repository;

import com.example.Marketing.model.SavedDashboardView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SavedDashboardViewRepository extends JpaRepository<SavedDashboardView, Integer> {

    List<SavedDashboardView> findByUserUserId(Integer userId);
}