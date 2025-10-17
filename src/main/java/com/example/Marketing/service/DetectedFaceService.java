package com.example.Marketing.service;

import com.example.Marketing.dto.DetectedFaceRequest;
import com.example.Marketing.dto.DetectedFaceResponse;
import java.util.List;

public interface DetectedFaceService {
    List<DetectedFaceResponse> findAll();
    DetectedFaceResponse findById(Integer faceId);
    List<DetectedFaceResponse> findByVisualAnalysisId(Integer visualAnalysisId);
    DetectedFaceResponse create(DetectedFaceRequest request);
    DetectedFaceResponse update(Integer faceId, DetectedFaceRequest request);
    void delete(Integer faceId);
}