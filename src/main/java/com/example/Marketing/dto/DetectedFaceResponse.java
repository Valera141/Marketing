package com.example.Marketing.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Builder
public class DetectedFaceResponse {

    @JsonProperty("face_id")
    private Integer detectedFaceId;

    @JsonProperty("visual_analysis_id")
    private Integer visualAnalysisId;

    @JsonProperty("main_emotion")
    private String mainEmotion;

    @JsonProperty("confidence_score")
    private BigDecimal confidenceScore;
}