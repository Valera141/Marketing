package com.example.Marketing.controller;

import com.example.Marketing.dto.ProcessingQueueRequest;
import com.example.Marketing.dto.ProcessingQueueResponse;
import com.example.Marketing.dto.ProcessingQueueStatusUpdateRequest;
import com.example.Marketing.service.ProcessingQueueService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/processing-queue")
@RequiredArgsConstructor
@Tag(name = "Processing Queue", description = "API para gestionar la Cola de Procesamiento de Trabajos")
public class ProcessingQueueController {

    private final ProcessingQueueService service;

    @Operation(summary = "Encolar un nuevo trabajo para procesamiento")
    @ApiResponse(responseCode = "201", description = "Trabajo encolado exitosamente")
    @PostMapping
    public ResponseEntity<ProcessingQueueResponse> enqueueJob(@Valid @RequestBody ProcessingQueueRequest request) {
        ProcessingQueueResponse createdJob = service.createJob(request);
        return new ResponseEntity<>(createdJob, HttpStatus.CREATED);
    }

    @Operation(summary = "Obtener todos los trabajos de la cola (paginado)")
    @GetMapping
    public Page<ProcessingQueueResponse> getAllJobs(@PageableDefault(size = 20, sort = "creationDate") Pageable pageable) {
        return service.findAll(pageable);
    }

    @Operation(summary = "Obtener un trabajo específico por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Trabajo encontrado"),
        @ApiResponse(responseCode = "404", description = "Trabajo no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProcessingQueueResponse> getJobById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @Operation(summary = "Actualizar el estado de un trabajo (usado por workers)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Estado del trabajo actualizado"),
        @ApiResponse(responseCode = "404", description = "Trabajo no encontrado")
    })
    @PutMapping("/{id}/status")
    public ResponseEntity<ProcessingQueueResponse> updateJobStatus(@PathVariable Integer id, @Valid @RequestBody ProcessingQueueStatusUpdateRequest request) {
        return ResponseEntity.ok(service.updateJobStatus(id, request));
    }
}