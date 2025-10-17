package com.example.Marketing.dto;

import java.math.BigDecimal;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VisualTagResponse{

    private Integer visualTagId;
    private Integer visualAnalysisId;
    private String tag;
    private BigDecimal confidenceScore;
}