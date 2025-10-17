package com.example.Marketing.controller;

import com.example.Marketing.dto.VisualAnalysisRequest;
import com.example.Marketing.dto.VisualAnalysisResponse;
import com.example.Marketing.service.VisualAnalysisService;
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

@RestController
@RequestMapping("/api/v1/visual-analyses")
@RequiredArgsConstructor
@Tag(name = "Visual Analysis", description = "API para gestionar los resultados de Análisis Visuales")
public class VisualAnalysisController {

    private final VisualAnalysisService service;

    @Operation(summary = "Crear un nuevo análisis visual para un recurso")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Análisis creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "El recurso ya tiene un análisis asociado"),
        @ApiResponse(responseCode = "404", description = "Recurso multimedia no encontrado")
    })
    @PostMapping
    public ResponseEntity<VisualAnalysisResponse> create(@Valid @RequestBody VisualAnalysisRequest request) {
        VisualAnalysisResponse createdAnalysis = service.create(request);
        return new ResponseEntity<>(createdAnalysis, HttpStatus.CREATED);
    }

    @Operation(summary = "Obtener un análisis visual por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Análisis encontrado",
            content = @Content(schema = @Schema(implementation = VisualAnalysisResponse.class))),
        @ApiResponse(responseCode = "404", description = "Análisis no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<VisualAnalysisResponse> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }
    
    /*
    // --- TEMPORALMENTE COMENTADO ---
    // Este endpoint depende directamente del método findByResourceId en el servicio,
    // que fue comentado porque requiere la relación con MultimediaResource.
    // Descomentar cuando la funcionalidad de MultimediaResource esté activa.
    @Operation(summary = "Obtener un análisis visual por el ID del recurso multimedia")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Análisis encontrado"),
        @ApiResponse(responseCode = "404", description = "No se encontró análisis para el recurso especificado")
    })
    @GetMapping("/resource/{resourceId}")
    public ResponseEntity<VisualAnalysisResponse> getByResourceId(@PathVariable Integer resourceId) {
        return ResponseEntity.ok(service.findByResourceId(resourceId));
    }
    */

    @Operation(summary = "Actualizar un análisis visual existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Análisis actualizado"),
        @ApiResponse(responseCode = "404", description = "Análisis o recurso no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<VisualAnalysisResponse> update(@PathVariable Integer id, @Valid @RequestBody VisualAnalysisRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @Operation(summary = "Eliminar un análisis visual")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Análisis eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Análisis no encontrado para eliminar")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}