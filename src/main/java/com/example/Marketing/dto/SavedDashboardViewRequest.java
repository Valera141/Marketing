package com.example.Marketing.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SavedDashboardViewRequest {

    @NotNull(message = "El ID de usuario es obligatorio.")
    private Integer userId;

    @NotBlank(message = "El nombre de la vista no puede estar vacío.")
    @Size(max = 100, message = "El nombre de la vista no puede exceder los 100 caracteres.")
    private String viewName;

    @NotBlank(message = "La configuración JSON no puede estar vacía.")
    private String configurationJson;
}