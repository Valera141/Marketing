package com.example.Marketing.mapper;

import com.example.Marketing.dto.VisualTagRequest;
import com.example.Marketing.dto.VisualTagResponse;
import com.example.Marketing.model.VisualTag;

public class VisualTagMapper {

    /**
     * Convierte una entidad VisualTag a un VisualTagResponse DTO.
     */
    public static VisualTagResponse toResponse(VisualTag visualTag) {
        if (visualTag == null) {
            return null;
        }

        VisualTagResponse response = new VisualTagResponse();
        response.setVisualTagId(visualTag.getVisualTagId());
        response.setTag(visualTag.getTag());
        response.setConfidenceScore(visualTag.getConfidenceScore());
        if (visualTag.getVisualAnalysis() != null) {
            response.setVisualAnalysisId(visualTag.getVisualAnalysis().getVisualAnalysisId());
        }
        return response;
    }

    /**
     * Convierte un VisualTagRequest DTO a una entidad VisualTag.
     * La asignación de VisualAnalysis se hace en la capa de servicio.
     */
    public static VisualTag toEntity(VisualTagRequest request) {
        if (request == null) {
            return null;
        }

        VisualTag visualTag = new VisualTag();
        visualTag.setTag(request.getTag());
        visualTag.setConfidenceScore(request.getConfidenceScore());
        return visualTag;
    }
}