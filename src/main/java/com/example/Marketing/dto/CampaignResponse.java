package com.example.Marketing.dto;

import lombok.Data;
import java.time.OffsetDateTime;
import java.util.List;

@Data
public class CampaignResponse {

    private Integer campaignId;
    private String campaignName;
    private Boolean isActive;
    private OffsetDateTime creationDate;
    private Integer creatorUserId; // ID del usuario creador
    private String creatorUserFullName; // Nombre del usuario creador para conveniencia
    private List<PublicationSummaryResponse> publications; // DTO resumido para evitar bucles

    // DTO anidado para las publicaciones
    @Data
    public static class PublicationSummaryResponse {
        private Integer publicationApiId;
        private String textContent;
        private OffsetDateTime publicationDate;
    }
}