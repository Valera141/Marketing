package com.example.Marketing.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "text_analysis")
public class TextAnalysis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "text_analysis_id")
    private Integer textAnalysisId;

    // 🔹 Relación 1:1 con Publication (cada publicación tiene un análisis de texto único)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publication_api_id", referencedColumnName = "publication_api_id", nullable = false)
    private Publication publication;

    @Column(name = "sentiment", length = 20)
    private String sentiment;

    @Column(name = "sentiment_confidence_score", precision = 5, scale = 4)
    private BigDecimal sentimentConfidenceScore;

    @Column(name = "detected_language", length = 10)
    private String detectedLanguage;

    @Column(name = "crisis_score", precision = 5, scale = 2)
    private BigDecimal crisisScore = BigDecimal.ZERO;

    @Column(name = "analysis_date", nullable = false)
    private OffsetDateTime analysisDate = OffsetDateTime.now();
}
