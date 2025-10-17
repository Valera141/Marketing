package com.example.Marketing.mapper;

import com.example.Marketing.dto.SearchTermRequest;
import com.example.Marketing.dto.SearchTermResponse;
import com.example.Marketing.model.SearchTerm;

public class SearchTermMapper {

    /**
     * Convierte una entidad SearchTerm a un SearchTermResponse DTO.
     */
    public static SearchTermResponse toResponse(SearchTerm searchTerm) {
        if (searchTerm == null) {
            return null;
        }

        SearchTermResponse response = new SearchTermResponse();
        response.setTermId(searchTerm.getTermId());
        response.setTerm(searchTerm.getTerm());
        response.setActive(searchTerm.isActive());
        if (searchTerm.getCampaign() != null) {
            response.setCampaignId(searchTerm.getCampaign().getCampaignId());
        }
        return response;
    }

    /**
     * Convierte un SearchTermRequest DTO a una entidad SearchTerm.
     * La asignación de la Campaign se hace en la capa de servicio.
     */
    public static SearchTerm toEntity(SearchTermRequest request) {
        if (request == null) {
            return null;
        }

        SearchTerm searchTerm = new SearchTerm();
        searchTerm.setTerm(request.getTerm());
        searchTerm.setActive(request.isActive());
        return searchTerm;
    }
}