package com.example.Marketing.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NotificationChannelRequest{

    @NotBlank(message = "El tipo de canal no puede estar vacío.")
    @Size(max = 50, message = "El tipo de canal no puede exceder los 50 caracteres.")
    private String channelType;

    @NotBlank(message = "La configuración no puede estar vacía.")
    private String configuration; // Se valida como JSON en el servicio

    @NotBlank(message = "El nombre del canal no puede estar vacío.")
    @Size(max = 100, message = "El nombre no puede exceder los 100 caracteres.")
    private String name;
}