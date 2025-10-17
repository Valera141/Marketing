package com.example.Marketing.mapper;

import com.example.Marketing.dto.CampaignResponse;
import com.example.Marketing.model.Campaign;
import com.example.Marketing.model.Publication;

import java.util.stream.Collectors;

public class CampaignMapper {

    public static CampaignResponse toResponse(Campaign campaign) {
        if (campaign == null) {
            return null;
        }

        CampaignResponse response = new CampaignResponse();
        response.setCampaignId(campaign.getCampaignId());
        response.setCampaignName(campaign.getCampaignName());
        response.setIsActive(campaign.getIsActive());
        response.setCreationDate(campaign.getCreationDate());

        if (campaign.getCreatorUser() != null) {
            response.setCreatorUserId(campaign.getCreatorUser().getUserId());
            response.setCreatorUserFullName(campaign.getCreatorUser().getFullName());
        }

        if (campaign.getPublications() != null) {
            response.setPublications(
                campaign.getPublications().stream()
                    .map(CampaignMapper::toPublicationSummary)
                    .collect(Collectors.toList())
            );
        }

        return response;
    }

    private static CampaignResponse.PublicationSummaryResponse toPublicationSummary(Publication publication) {
        CampaignResponse.PublicationSummaryResponse summary = new CampaignResponse.PublicationSummaryResponse();
        summary.setPublicationApiId(publication.getPublicationApiId());
        summary.setTextContent(publication.getTextContent());
        summary.setPublicationDate(publication.getPublicationDate());
        return summary;
    }
}