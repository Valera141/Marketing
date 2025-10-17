package com.example.Marketing.service;

import com.example.Marketing.dto.VisualTagRequest;
import com.example.Marketing.dto.VisualTagResponse;
import java.util.List;

public interface VisualTagService {
    VisualTagResponse createVisualTag(VisualTagRequest request);
    List<VisualTagResponse> getTagsByAnalysisId(Integer analysisId);
}