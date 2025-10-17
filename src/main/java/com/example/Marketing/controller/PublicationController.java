package com.example.Marketing.controller;

import com.example.Marketing.dto.PublicationRequestDTO;
import com.example.Marketing.dto.PublicationResponseDTO;
import com.example.Marketing.service.PublicationService; // ÚNICA dependencia de servicio necesaria
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/publications") // Ruta base estandarizada
@Tag(name = "Publications", description = "API para la gestión de Publicaciones y la generación de alertas")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class PublicationController {

    // --- CORRECCIÓN: Ahora solo hay una dependencia para todo ---
    private final PublicationService publicationService;

    // =======================================================
    // === Endpoints CRUD para la gestión de Publicaciones ===
    // =======================================================

    @Operation(summary = "Crear una nueva publicación")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Publicación creada"),
        @ApiResponse(responseCode = "404", description = "Publicación no encontrada")
    })
    @PostMapping
    public ResponseEntity<PublicationResponseDTO> createPublication(@Valid @RequestBody PublicationRequestDTO requestDTO) {
        PublicationResponseDTO createdPublication = publicationService.create(requestDTO);
        return new ResponseEntity<>(createdPublication, HttpStatus.CREATED);
    }

    @Operation(summary = "Obtener una publicación por su ID")@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Publicación encontrada"),
        @ApiResponse(responseCode = "404", description = "Publicación no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<PublicationResponseDTO> getPublicationById(@PathVariable Integer id) {
        PublicationResponseDTO publication = publicationService.findById(id);
        return ResponseEntity.ok(publication);
    }

    @Operation(summary = "Obtener todas las publicaciones con paginación")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de publicaciones"),
        @ApiResponse(responseCode = "404", description = "Publicación no encontrada")
    })
    @GetMapping
    public ResponseEntity<Page<PublicationResponseDTO>> getAllPublications(Pageable pageable) {
        Page<PublicationResponseDTO> publications = publicationService.getAllPublications(pageable);
        return ResponseEntity.ok(publications);
    }

    @Operation(summary = "Actualizar una publicación existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Publicación actualizada"),
        @ApiResponse(responseCode = "404", description = "Publicación no encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<PublicationResponseDTO> updatePublication(@PathVariable Integer id, @Valid @RequestBody PublicationRequestDTO requestDTO) {
        PublicationResponseDTO updatedPublication = publicationService.update(id, requestDTO);
        return ResponseEntity.ok(updatedPublication);
    }

    @Operation(summary = "Eliminar una publicación por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Publicación eliminada"),
        @ApiResponse(responseCode = "404", description = "Publicación no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePublication(@PathVariable Integer id) {
        publicationService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    // =======================================================
    // === Endpoints para la generación de Alertas =========
    // =======================================================

    @Operation(summary = "Verificar si existe una ola de comentarios negativos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Verificación completada exitosamente"),
        @ApiResponse(responseCode = "404", description = "No se encontraron publicaciones que coincidan con los criterios.")
    })
    @GetMapping("/alerts/negative-wave")
    public ResponseEntity<Map<String, Object>> checkNegativeWave(@RequestParam Integer campaignId) {
        // --- CORRECCIÓN: Se usa el servicio unificado ---
        boolean hasWave = publicationService.hasNegativeWave(campaignId);
        Map<String, Object> response = Collections.singletonMap("negativeWaveDetected", hasWave);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Encontrar contenido con potencial viral")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Búsqueda completada"),
        @ApiResponse(responseCode = "404", description = "No se encontraron publicaciones que coincidan con los criterios.")
    })
    @GetMapping("/alerts/viral-potential")
    public ResponseEntity<List<PublicationResponseDTO>> findPotentialViralContent() {
        // --- CORRECCIÓN: Se usa el servicio unificado ---
        List<PublicationResponseDTO> publications = publicationService.findPotentialViralContent();
        return ResponseEntity.ok(publications);
    }

    @Operation(summary = "Encontrar actividad negativa de influencers")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Búsqueda completada"),
        @ApiResponse(responseCode = "404", description = "No se encontraron publicaciones que coincidan con los criterios.")
    })
    @GetMapping("/alerts/negative-influencers")
    public ResponseEntity<List<PublicationResponseDTO>> findNegativeInfluencerActivity() {
        // --- CORRECCIÓN: Se usa el servicio unificado ---
        List<PublicationResponseDTO> publications = publicationService.findNegativeInfluencerActivity();
        return ResponseEntity.ok(publications);
    }
}