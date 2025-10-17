package com.example.Marketing.service;

import com.example.Marketing.dto.SearchTermRequest;
import com.example.Marketing.dto.SearchTermResponse;
import java.util.List;

public interface SearchTermService {
    SearchTermResponse createSearchTerm(SearchTermRequest request);
    List<SearchTermResponse> getTermsByCampaignId(Integer campaignId);
    SearchTermResponse updateSearchTerm(Integer id, SearchTermRequest request);
    void deleteSearchTerm(Integer id);
}