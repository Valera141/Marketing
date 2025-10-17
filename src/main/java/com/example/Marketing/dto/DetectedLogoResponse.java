package com.example.Marketing.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Builder
public class DetectedLogoResponse {

    @JsonProperty("logo_id")
    private Integer detectedLogoId;

    @JsonProperty("visual_analysis_id")
    private Integer visualAnalysisId;

    @JsonProperty("detected_brand")
    private String detectedBrand;

    @JsonProperty("bounding_box_coords")
    private String boundingBoxCoords;

    @JsonProperty("confidence_score")
    private BigDecimal confidenceScore;
}