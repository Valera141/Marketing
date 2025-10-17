package com.example.Marketing.controller;

import com.example.Marketing.dto.SavedDashboardViewRequest;
import com.example.Marketing.dto.SavedDashboardViewResponse;
import com.example.Marketing.service.SavedDashboardViewService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/dashboard-views")
public class SavedDashboardViewController {

    private final SavedDashboardViewService dashboardViewService;

    public SavedDashboardViewController(SavedDashboardViewService dashboardViewService) {
        this.dashboardViewService = dashboardViewService;
    }

    @PostMapping
    public ResponseEntity<SavedDashboardViewResponse> createDashboardView(
            @Valid @RequestBody SavedDashboardViewRequest request) {
        SavedDashboardViewResponse createdView = dashboardViewService.createDashboardView(request);
        return new ResponseEntity<>(createdView, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SavedDashboardViewResponse> getDashboardViewById(@PathVariable Integer id) {
        SavedDashboardViewResponse view = dashboardViewService.getDashboardViewById(id);
        return ResponseEntity.ok(view);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<SavedDashboardViewResponse>> getViewsByUserId(@PathVariable Integer userId) {
        List<SavedDashboardViewResponse> views = dashboardViewService.getViewsByUserId(userId);
        return ResponseEntity.ok(views);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDashboardView(@PathVariable Integer id) {
        dashboardViewService.deleteDashboardView(id);
        return ResponseEntity.noContent().build();
    }
}