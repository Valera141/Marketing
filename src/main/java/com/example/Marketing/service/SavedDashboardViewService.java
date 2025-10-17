package com.example.Marketing.service;

import com.example.Marketing.dto.SavedDashboardViewRequest;
import com.example.Marketing.dto.SavedDashboardViewResponse;
import java.util.List;

public interface SavedDashboardViewService {
    SavedDashboardViewResponse createDashboardView(SavedDashboardViewRequest request);
    SavedDashboardViewResponse getDashboardViewById(Integer id);
    List<SavedDashboardViewResponse> getViewsByUserId(Integer userId);
    void deleteDashboardView(Integer id);
}