package com.example.Marketing.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SearchTermResponse{

    private Integer termId;
    private Integer campaignId;
    private String term;
    private boolean isActive;
}