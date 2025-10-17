package com.example.Marketing.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.Marketing.dto.SearchTermRequest;
import com.example.Marketing.dto.SearchTermResponse;
import com.example.Marketing.exception.ResourceNotFoundException;
import com.example.Marketing.model.Campaign;
import com.example.Marketing.model.SearchTerm;
import com.example.Marketing.repository.CampaignRepository;
import com.example.Marketing.repository.SearchTermRepository;

@Service
public class SearchTermServiceImpl implements SearchTermService {

    private final SearchTermRepository searchTermRepository;
    private final CampaignRepository campaignRepository;

    public SearchTermServiceImpl(SearchTermRepository searchTermRepository, CampaignRepository campaignRepository) {
        this.searchTermRepository = searchTermRepository;
        this.campaignRepository = campaignRepository;
    }

    @Override
    public SearchTermResponse createSearchTerm(SearchTermRequest request) {
        Campaign campaign = campaignRepository.findById(request.getCampaignId())
                .orElseThrow(() -> new ResourceNotFoundException("Campaign not found with id: " + request.getCampaignId()));

        SearchTerm searchTerm = new SearchTerm();
        searchTerm.setCampaign(campaign);
        searchTerm.setTerm(request.getTerm());
        searchTerm.setActive(request.isActive());

        SearchTerm savedTerm = searchTermRepository.save(searchTerm);
        return mapToResponse(savedTerm);
    }

    @Override
    public List<SearchTermResponse> getTermsByCampaignId(Integer campaignId) {
        if (!campaignRepository.existsById(campaignId)) {
            throw new ResourceNotFoundException("Campaign not found with id: " + campaignId);
        }
        return searchTermRepository.findByCampaignCampaignId(campaignId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public SearchTermResponse updateSearchTerm(Integer id, SearchTermRequest request) {
        SearchTerm searchTerm = searchTermRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("SearchTerm not found with id: " + id));
        
        Campaign campaign = campaignRepository.findById(request.getCampaignId())
                .orElseThrow(() -> new ResourceNotFoundException("Campaign not found with id: " + request.getCampaignId()));

        searchTerm.setCampaign(campaign);
        searchTerm.setTerm(request.getTerm());
        searchTerm.setActive(request.isActive());
        
        SearchTerm updatedTerm = searchTermRepository.save(searchTerm);
        return mapToResponse(updatedTerm);
    }

    @Override
    public void deleteSearchTerm(Integer id) {
        if (!searchTermRepository.existsById(id)) {
            throw new ResourceNotFoundException("Search term not found with id: " + id);
        }
        searchTermRepository.deleteById(id);
    }

    private SearchTermResponse mapToResponse(SearchTerm term) {
        SearchTermResponse response = new SearchTermResponse();
        response.setTermId(term.getTermId());
        response.setCampaignId(term.getCampaign().getCampaignId());
        response.setTerm(term.getTerm());
        response.setActive(term.isActive());
        return response;
    }
}