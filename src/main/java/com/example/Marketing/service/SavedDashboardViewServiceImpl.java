package com.example.Marketing.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.Marketing.dto.SavedDashboardViewRequest;
import com.example.Marketing.dto.SavedDashboardViewResponse;
import com.example.Marketing.exception.ResourceNotFoundException;
import com.example.Marketing.model.SavedDashboardView;
import com.example.Marketing.model.User;
import com.example.Marketing.repository.SavedDashboardViewRepository;
import com.example.Marketing.repository.UserRepository;

@Service
public class SavedDashboardViewServiceImpl implements SavedDashboardViewService {

    private final SavedDashboardViewRepository dashboardViewRepository;
    private final UserRepository userRepository; // Necesario para buscar el usuario

    public SavedDashboardViewServiceImpl(SavedDashboardViewRepository dashboardViewRepository, UserRepository userRepository) {
        this.dashboardViewRepository = dashboardViewRepository;
        this.userRepository = userRepository;
    }

    @Override
    public SavedDashboardViewResponse createDashboardView(SavedDashboardViewRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + request.getUserId()));

        SavedDashboardView view = new SavedDashboardView();
        view.setUser(user);
        view.setViewName(request.getViewName());
        view.setConfigurationJson(request.getConfigurationJson());

        SavedDashboardView savedView = dashboardViewRepository.save(view);
        return mapToResponse(savedView);
    }

    @Override
    public SavedDashboardViewResponse getDashboardViewById(Integer id) {
        SavedDashboardView view = dashboardViewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dashboard view not found with id: " + id));
        return mapToResponse(view);
    }

    @Override
    public List<SavedDashboardViewResponse> getViewsByUserId(Integer userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User not found with id: " + userId);
        }
        return dashboardViewRepository.findByUserUserId(userId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteDashboardView(Integer id) {
        if (!dashboardViewRepository.existsById(id)) {
            throw new ResourceNotFoundException("Dashboard view not found with id: " + id);
        }
        dashboardViewRepository.deleteById(id);
    }

    private SavedDashboardViewResponse mapToResponse(SavedDashboardView view) {
        SavedDashboardViewResponse response = new SavedDashboardViewResponse();
        response.setViewId(view.getViewId());
        response.setUserId(view.getUser().getUserId());
        response.setViewName(view.getViewName());
        response.setConfigurationJson(view.getConfigurationJson());
        return response;
    }
}