package com.example.Marketing.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.Marketing.dto.VisualTagRequest;
import com.example.Marketing.dto.VisualTagResponse;
import com.example.Marketing.exception.ResourceNotFoundException;
import com.example.Marketing.model.VisualAnalysis;
import com.example.Marketing.model.VisualTag;
import com.example.Marketing.repository.VisualAnalysisRepository;
import com.example.Marketing.repository.VisualTagRepository;

@Service
public class VisualTagServiceImpl implements VisualTagService {

    private final VisualTagRepository visualTagRepository;
    private final VisualAnalysisRepository visualAnalysisRepository;

    public VisualTagServiceImpl(VisualTagRepository visualTagRepository, VisualAnalysisRepository visualAnalysisRepository) {
        this.visualTagRepository = visualTagRepository;
        this.visualAnalysisRepository = visualAnalysisRepository;
    }

    @Override
    public VisualTagResponse createVisualTag(VisualTagRequest request) {
        VisualAnalysis analysis = visualAnalysisRepository.findById(request.getVisualAnalysisId())
                .orElseThrow(() -> new ResourceNotFoundException("Visual analysis not found with id: " + request.getVisualAnalysisId()));

        VisualTag visualTag = new VisualTag();
        visualTag.setVisualAnalysis(analysis);
        visualTag.setTag(request.getTag());
        visualTag.setConfidenceScore(request.getConfidenceScore());

        VisualTag savedTag = visualTagRepository.save(visualTag);
        return mapToResponse(savedTag);
    }

    @Override
    public List<VisualTagResponse> getTagsByAnalysisId(Integer analysisId) {
        if (!visualAnalysisRepository.existsById(analysisId)) {
            throw new ResourceNotFoundException("Visual analysis not found with id: " + analysisId);
        }
        return visualTagRepository.findByVisualAnalysisVisualAnalysisId(analysisId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private VisualTagResponse mapToResponse(VisualTag tag) {
        VisualTagResponse response = new VisualTagResponse();
        response.setVisualTagId(tag.getVisualTagId());
        response.setVisualAnalysisId(tag.getVisualAnalysis().getVisualAnalysisId());
        response.setTag(tag.getTag());
        response.setConfidenceScore(tag.getConfidenceScore());
        return response;
    }
}