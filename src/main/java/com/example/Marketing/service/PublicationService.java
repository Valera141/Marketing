package com.example.Marketing.service;

import com.example.Marketing.dto.PublicationRequestDTO;
import com.example.Marketing.dto.PublicationResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PublicationService {

    // --- Métodos CRUD para la gestión de Publicaciones ---

    PublicationResponseDTO create(PublicationRequestDTO request);

    PublicationResponseDTO findById(Integer publicationId);

    List<PublicationResponseDTO> findAll();

    Page<PublicationResponseDTO> getAllPublications(Pageable pageable);

    PublicationResponseDTO update(Integer publicationId, PublicationRequestDTO request);

    void delete(Integer publicationId);
    
    List<PublicationResponseDTO> findByCampaignId(Integer campaignId);


    // --- Métodos de Lógica de Negocio para Alertas ---

    boolean hasNegativeWave(Integer campaignId);

    List<PublicationResponseDTO> findPotentialViralContent();

    List<PublicationResponseDTO> findNegativeInfluencerActivity();
}