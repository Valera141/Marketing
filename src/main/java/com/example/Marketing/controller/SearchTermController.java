package com.example.Marketing.controller;

import com.example.Marketing.dto.SearchTermRequest;
import com.example.Marketing.dto.SearchTermResponse;
import com.example.Marketing.service.SearchTermService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/search-terms")
public class SearchTermController {

    private final SearchTermService searchTermService;

    public SearchTermController(SearchTermService searchTermService) {
        this.searchTermService = searchTermService;
    }

    @PostMapping
    public ResponseEntity<SearchTermResponse> createSearchTerm(
            @Valid @RequestBody SearchTermRequest request) {
        SearchTermResponse createdTerm = searchTermService.createSearchTerm(request);
        return new ResponseEntity<>(createdTerm, HttpStatus.CREATED);
    }
    
    @GetMapping("/campaign/{campaignId}")
    public ResponseEntity<List<SearchTermResponse>> getTermsByCampaignId(@PathVariable Integer campaignId) {
        List<SearchTermResponse> terms = searchTermService.getTermsByCampaignId(campaignId);
        return ResponseEntity.ok(terms);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<SearchTermResponse> updateSearchTerm(
            @PathVariable Integer id, 
            @Valid @RequestBody SearchTermRequest request) {
        SearchTermResponse updatedTerm = searchTermService.updateSearchTerm(id, request);
        return ResponseEntity.ok(updatedTerm);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSearchTerm(@PathVariable Integer id) {
        searchTermService.deleteSearchTerm(id);
        return ResponseEntity.noContent().build();
    }
}