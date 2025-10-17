package com.example.Marketing.service;

import com.example.Marketing.dto.CampaignRequest;
import com.example.Marketing.dto.CampaignResponse;

import java.util.List;

public interface CampaignService {
    CampaignResponse createCampaign(CampaignRequest request);
    CampaignResponse getCampaignById(Integer id);
    List<CampaignResponse> getAllCampaigns();
    CampaignResponse updateCampaign(Integer id, CampaignRequest request);
    void deleteCampaign(Integer id);
}