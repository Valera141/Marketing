package com.example.Marketing.mapper;

import com.example.Marketing.dto.SavedDashboardViewRequest;
import com.example.Marketing.dto.SavedDashboardViewResponse;
import com.example.Marketing.model.SavedDashboardView;

public class SavedDashboardViewMapper {

    /**
     * Convierte una entidad SavedDashboardView a un SavedDashboardViewResponse DTO.
     */
    public static SavedDashboardViewResponse toResponse(SavedDashboardView view) {
        if (view == null) {
            return null;
        }

        SavedDashboardViewResponse response = new SavedDashboardViewResponse();
        response.setViewId(view.getViewId());
        response.setViewName(view.getViewName());
        response.setConfigurationJson(view.getConfigurationJson());
        if (view.getUser() != null) {
            response.setUserId(view.getUser().getUserId());
        }
        return response;
    }

    /**
     * Convierte un SavedDashboardViewRequest DTO a una entidad SavedDashboardView.
     * La asignación del User se hace en la capa de servicio.
     */
    public static SavedDashboardView toEntity(SavedDashboardViewRequest request) {
        if (request == null) {
            return null;
        }

        SavedDashboardView view = new SavedDashboardView();
        view.setViewName(request.getViewName());
        view.setConfigurationJson(request.getConfigurationJson());
        // El objeto User completo se asigna en el servicio, aquí solo preparamos la entidad.
        return view;
    }
}