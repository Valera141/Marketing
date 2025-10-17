package com.example.Marketing.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VisualTagRequest{

    @NotNull(message = "El ID del análisis visual es obligatorio.")
    private Integer visualAnalysisId;

    @NotBlank(message = "La etiqueta no puede estar vacía.")
    @Size(max = 100, message = "La etiqueta no puede exceder los 100 caracteres.")
    private String tag;

    @NotNull(message = "La puntuación de confianza es obligatoria.")
    @DecimalMin(value = "0.0", message = "La puntuación de confianza debe ser como mínimo 0.0.")
    @DecimalMax(value = "1.0", message = "La puntuación de confianza debe ser como máximo 1.0.")
    private BigDecimal confidenceScore;
}