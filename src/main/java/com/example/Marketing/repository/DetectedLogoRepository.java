package com.example.Marketing.repository;

import com.example.Marketing.model.DetectedLogo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DetectedLogoRepository extends JpaRepository<DetectedLogo, Integer> {
    
    // Finds all logos associated with a specific visual analysis
    List<DetectedLogo> findByVisualAnalysis_VisualAnalysisId(Integer visualAnalysisId);
}