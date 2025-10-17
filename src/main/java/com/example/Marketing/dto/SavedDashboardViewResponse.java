package com.example.Marketing.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SavedDashboardViewResponse{

    private Integer viewId;
    private Integer userId; // O un DTO de usuario más completo si es necesario
    private String viewName;
    private String configurationJson;
}