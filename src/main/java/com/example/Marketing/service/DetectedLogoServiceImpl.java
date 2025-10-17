package com.example.Marketing.service;

import com.example.Marketing.dto.DetectedLogoRequest;
import com.example.Marketing.dto.DetectedLogoResponse;
import com.example.Marketing.mapper.DetectedLogoMapper;
import com.example.Marketing.model.DetectedLogo;
import com.example.Marketing.model.VisualAnalysis;
import com.example.Marketing.repository.DetectedLogoRepository;
import com.example.Marketing.repository.VisualAnalysisRepository; // Assuming this repository exists
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class DetectedLogoServiceImpl implements DetectedLogoService {

    private final DetectedLogoRepository logoRepository;
    private final VisualAnalysisRepository visualAnalysisRepository;

    @Override
    @Transactional(readOnly = true)
    public List<DetectedLogoResponse> findAll() {
        return logoRepository.findAll().stream()
                .map(DetectedLogoMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public DetectedLogoResponse findById(Integer logoId) {
        return logoRepository.findById(logoId)
                .map(DetectedLogoMapper::toResponse)
                .orElseThrow(() -> new EntityNotFoundException("Logo detectado no encontrado con ID: " + logoId));
    }

    @Override
    @Transactional(readOnly = true)
    public List<DetectedLogoResponse> findByVisualAnalysisId(Integer visualAnalysisId) {
        return logoRepository.findByVisualAnalysis_VisualAnalysisId(visualAnalysisId).stream()
                .map(DetectedLogoMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public DetectedLogoResponse create(DetectedLogoRequest request) {
        VisualAnalysis visualAnalysis = visualAnalysisRepository.findById(request.visualAnalysisId())
                .orElseThrow(() -> new EntityNotFoundException("Análisis visual no encontrado con ID: " + request.visualAnalysisId()));
        
        DetectedLogo newLogo = DetectedLogoMapper.toEntity(request, visualAnalysis);
        return DetectedLogoMapper.toResponse(logoRepository.save(newLogo));
    }

    @Override
    public DetectedLogoResponse update(Integer logoId, DetectedLogoRequest request) {
        DetectedLogo existingLogo = logoRepository.findById(logoId)
                .orElseThrow(() -> new EntityNotFoundException("Logo detectado no encontrado con ID: " + logoId));

        DetectedLogoMapper.copyToEntity(request, existingLogo);

        if (!existingLogo.getVisualAnalysis().getVisualAnalysisId().equals(request.visualAnalysisId())) {
            VisualAnalysis newVisualAnalysis = visualAnalysisRepository.findById(request.visualAnalysisId())
                    .orElseThrow(() -> new EntityNotFoundException("Análisis visual no encontrado con ID: " + request.visualAnalysisId()));
            existingLogo.setVisualAnalysis(newVisualAnalysis);
        }

        return DetectedLogoMapper.toResponse(logoRepository.save(existingLogo));
    }

    @Override
    public void delete(Integer logoId) {
        if (!logoRepository.existsById(logoId)) {
            throw new EntityNotFoundException("Logo detectado no encontrado con ID: " + logoId);
        }
        logoRepository.deleteById(logoId);
    }
}