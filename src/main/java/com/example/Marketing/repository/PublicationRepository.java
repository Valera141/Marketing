package com.example.Marketing.repository;

import com.example.Marketing.model.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;

@Repository
public interface PublicationRepository extends JpaRepository<Publication, Integer> {

    // --- Métodos de Búsqueda Estándar (Derived Queries) ---

    List<Publication> findByCampaign_CampaignId(Integer campaignId);

    List<Publication> findByAuthor_AuthorId(Integer authorId);

    
    // --- Métodos para Alertas y Reglas de Negocio (JPQL) ---

    /**
     * Regla 2: Cuenta publicaciones negativas recientes para una campaña.
     */
    @Query("SELECT COUNT(p) FROM Publication p JOIN p.textAnalysis ta " +
           "WHERE p.campaign.campaignId = :campaignId AND ta.sentiment = 'Negative' AND p.collectionDate >= :sinceDateTime")
    long countRecentNegativeByCampaignJPQL(
            @Param("campaignId") Integer campaignId,
            @Param("sinceDateTime") OffsetDateTime sinceDateTime
    );

    /**
     * Regla 5: Encuentra publicaciones con potencial de volverse virales.
     */
    @Query("SELECT p FROM Publication p LEFT JOIN p.textAnalysis ta " +
           "WHERE p.likes > :minLikes AND p.shares > :minShares " +
           "AND (ta.sentiment IS NULL OR ta.sentiment <> 'Negative') " +
           "AND p.publicationDate >= :timeLimit")
    List<Publication> findPotentialViralContentJPQL(
            @Param("minLikes") int minLikes,
            @Param("minShares") int minShares,
            @Param("timeLimit") OffsetDateTime timeLimit
    );

    /**
     * Regla 1 y 4: Busca publicaciones de influencers según criterios de sentimiento y seguidores.
     */
    @Query("SELECT p FROM Publication p JOIN p.author a JOIN p.textAnalysis ta " +
           "WHERE ta.sentiment = :sentiment " +
           "AND ta.sentimentConfidenceScore > :minConfidence " +
           "AND a.followerCount > :minFollowers")
    List<Publication> findPublicationsByInfluencerCriteriaJPQL(
            @Param("sentiment") String sentiment,
            @Param("minConfidence") double minConfidence,
            @Param("minFollowers") int minFollowers
    );
}