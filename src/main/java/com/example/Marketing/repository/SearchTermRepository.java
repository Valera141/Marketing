package com.example.Marketing.repository;

import com.example.Marketing.model.SearchTerm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchTermRepository extends JpaRepository<SearchTerm, Integer> {

    List<SearchTerm> findByCampaignCampaignId(Integer campaignId);
}