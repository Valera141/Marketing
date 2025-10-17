package com.example.Marketing.controller;

import com.example.Marketing.dto.VisualTagRequest;
import com.example.Marketing.dto.VisualTagResponse;
import com.example.Marketing.service.VisualTagService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/visual-tags")
public class VisualTagController {

    private final VisualTagService visualTagService;

    public VisualTagController(VisualTagService visualTagService) {
        this.visualTagService = visualTagService;
    }

    @PostMapping
    public ResponseEntity<VisualTagResponse> createVisualTag(
            @Valid @RequestBody VisualTagRequest request) {
        VisualTagResponse createdTag = visualTagService.createVisualTag(request);
        return new ResponseEntity<>(createdTag, HttpStatus.CREATED);
    }

    @GetMapping("/analysis/{analysisId}")
    public ResponseEntity<List<VisualTagResponse>> getTagsByAnalysisId(@PathVariable Integer analysisId) {
        List<VisualTagResponse> tags = visualTagService.getTagsByAnalysisId(analysisId);
        return ResponseEntity.ok(tags);
    }
}