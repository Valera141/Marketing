package com.example.Marketing.service;

import com.example.Marketing.dto.DetectedLogoRequest;
import com.example.Marketing.dto.DetectedLogoResponse;
import java.util.List;

public interface DetectedLogoService {
    List<DetectedLogoResponse> findAll();
    DetectedLogoResponse findById(Integer logoId);
    List<DetectedLogoResponse> findByVisualAnalysisId(Integer visualAnalysisId);
    DetectedLogoResponse create(DetectedLogoRequest request);
    DetectedLogoResponse update(Integer logoId, DetectedLogoRequest request);
    void delete(Integer logoId);
}