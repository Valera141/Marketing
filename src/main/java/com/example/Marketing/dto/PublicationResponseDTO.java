package com.example.Marketing.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@Builder // <-- ¡Esta es la anotación que faltaba y soluciona el error!
@NoArgsConstructor
@AllArgsConstructor
public class PublicationResponseDTO {

    @JsonProperty("publication_api_id")
    private Integer publicationApiId;

    @JsonProperty("campaign_id")
    private Integer campaignId;

    @JsonProperty("campaign_name")
    private String campaignName;
    
    @JsonProperty("author_api_id")
    private Integer authorApiId;
    
    @JsonProperty("author_username")
    private String authorUsername;

    @JsonProperty("text_content")
    private String textContent;

    @JsonProperty("publication_date")
    private OffsetDateTime publicationDate;

    private Integer likes;
    private Integer comments;
    private Integer shares;
    private String publicationUrl;
    private String classificationPriority;
    private OffsetDateTime collectionDate;
}