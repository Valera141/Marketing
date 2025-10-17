package com.example.Marketing.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SearchTermRequest {

    @NotNull(message = "El ID de la campaña es obligatorio.")
    private Integer campaignId;

    @NotBlank(message = "El término de búsqueda no puede estar vacío.")
    @Size(max = 255, message = "El término no puede exceder los 255 caracteres.")
    private String term;

    private boolean isActive = true;
}