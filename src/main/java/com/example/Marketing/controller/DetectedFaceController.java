package com.example.Marketing.controller;

import com.example.Marketing.dto.DetectedFaceRequest;
import com.example.Marketing.dto.DetectedFaceResponse;
import com.example.Marketing.service.DetectedFaceService;
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
@RequestMapping("/api/v1/detected-faces")
@RequiredArgsConstructor
@Tag(name = "Detected Faces", description = "API para gestionar las Caras Detectadas en análisis visuales")
public class DetectedFaceController {

    private final DetectedFaceService service;

    @Operation(summary = "Obtener todas las caras detectadas")
    @GetMapping
    public List<DetectedFaceResponse> getAll() {
        return service.findAll();
    }

    @Operation(summary = "Obtener una cara detectada por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cara encontrada",
            content = @Content(schema = @Schema(implementation = DetectedFaceResponse.class))),
        @ApiResponse(responseCode = "404", description = "Cara no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<DetectedFaceResponse> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }
    
    @Operation(summary = "Obtener todas las caras para un análisis visual específico")
    @ApiResponse(responseCode = "200", description = "Lista de caras para el análisis")
    @GetMapping("/analysis/{visualAnalysisId}")
    public List<DetectedFaceResponse> getByVisualAnalysisId(@PathVariable Integer visualAnalysisId) {
        return service.findByVisualAnalysisId(visualAnalysisId);
    }

    @Operation(summary = "Crear un nuevo registro de cara detectada")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Registro de cara creado"),
        @ApiResponse(responseCode = "404", description = "Análisis visual no encontrado")
    })
    @PostMapping
    public ResponseEntity<DetectedFaceResponse> create(@Valid @RequestBody DetectedFaceRequest request) {
        DetectedFaceResponse createdFace = service.create(request);
        return new ResponseEntity<>(createdFace, HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar un registro de cara detectada")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Registro actualizado"),
        @ApiResponse(responseCode = "404", description = "Cara o análisis visual no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<DetectedFaceResponse> update(@PathVariable Integer id, @Valid @RequestBody DetectedFaceRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @Operation(summary = "Eliminar un registro de cara detectada")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Registro eliminado"),
        @ApiResponse(responseCode = "404", description = "Cara no encontrada para eliminar")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}