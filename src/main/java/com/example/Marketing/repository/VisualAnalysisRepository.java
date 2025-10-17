package com.example.Marketing.repository;

import com.example.Marketing.model.VisualAnalysis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisualAnalysisRepository extends JpaRepository<VisualAnalysis, Integer> {
}