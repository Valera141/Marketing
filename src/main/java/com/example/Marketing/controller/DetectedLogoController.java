package com.example.Marketing.controller;

import com.example.Marketing.dto.DetectedLogoRequest;
import com.example.Marketing.dto.DetectedLogoResponse;
import com.example.Marketing.service.DetectedLogoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/detected-logos")
@RequiredArgsConstructor
@Tag(name = "Detected Logos", description = "API para gestionar los Logos Detectados en un análisis")
public class DetectedLogoController {

    private final DetectedLogoService service;

    @Operation(summary = "Obtener todos los logos detectados")
    @GetMapping
    public List<DetectedLogoResponse> getAll() {
        return service.findAll();
    }

    @Operation(summary = "Obtener un logo detectado por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Logo encontrado",
            content = @Content(schema = @Schema(implementation = DetectedLogoResponse.class))),
        @ApiResponse(responseCode = "404", description = "Logo no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<DetectedLogoResponse> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }
    
    @Operation(summary = "Obtener todos los logos para un análisis visual específico")
    @ApiResponse(responseCode = "200", description = "Lista de logos para el análisis")
    @GetMapping("/analysis/{visualAnalysisId}")
    public List<DetectedLogoResponse> getByVisualAnalysisId(@PathVariable Integer visualAnalysisId) {
        return service.findByVisualAnalysisId(visualAnalysisId);
    }

    @Operation(summary = "Crear un nuevo registro de logo detectado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Logo creado"),
        @ApiResponse(responseCode = "404", description = "Análisis visual no encontrado")
    })
    @PostMapping
    public ResponseEntity<DetectedLogoResponse> create(@Valid @RequestBody DetectedLogoRequest request) {
        DetectedLogoResponse createdLogo = service.create(request);
        return new ResponseEntity<>(createdLogo, HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar un registro de logo detectado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Logo actualizado"),
        @ApiResponse(responseCode = "404", description = "Logo o análisis visual no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<DetectedLogoResponse> update(@PathVariable Integer id, @Valid @RequestBody DetectedLogoRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @Operation(summary = "Eliminar un registro de logo detectado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Logo eliminado"),
        @ApiResponse(responseCode = "404", description = "Logo no encontrado para eliminar")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}