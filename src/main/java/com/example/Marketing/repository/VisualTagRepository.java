package com.example.Marketing.repository;

import com.example.Marketing.model.VisualTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisualTagRepository extends JpaRepository<VisualTag, Integer> {

    List<VisualTag> findByVisualAnalysisVisualAnalysisId(Integer visualAnalysisId);
}