package com.example.Marketing.service;

import com.example.Marketing.dto.CampaignRequest;
import com.example.Marketing.dto.CampaignResponse;
import com.example.Marketing.exception.ResourceNotFoundException;
import com.example.Marketing.model.Campaign;
import com.example.Marketing.model.User;
import com.example.Marketing.repository.CampaignRepository;
import com.example.Marketing.repository.UserRepository;
import org.modelmapper.ModelMapper; // 1. Importar ModelMapper
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CampaignServiceImpl implements CampaignService {

    private final CampaignRepository campaignRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper; // 2. Añadir el campo

    // 3. Inyectar ModelMapper en el constructor
    public CampaignServiceImpl(CampaignRepository campaignRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.campaignRepository = campaignRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CampaignResponse createCampaign(CampaignRequest request) {
        User creator = userRepository.findById(request.getCreatorUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + request.getCreatorUserId()));

        Campaign campaign = new Campaign();
        campaign.setCampaignName(request.getCampaignName());
        campaign.setIsActive(request.getIsActive());
        campaign.setCreatorUser(creator);
        campaign.setCreationDate(OffsetDateTime.now());

        Campaign savedCampaign = campaignRepository.save(campaign);
        // Usamos modelMapper para la conversión
        return modelMapper.map(savedCampaign, CampaignResponse.class);
    }

    @Override
    public CampaignResponse getCampaignById(Integer id) {
        Campaign campaign = campaignRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Campaign not found with id: " + id));
        // Mapeo simplificado en una línea
        return modelMapper.map(campaign, CampaignResponse.class);
    }

    @Override
    public List<CampaignResponse> getAllCampaigns() {
        return campaignRepository.findAll().stream()
                // Mapeo simplificado dentro del stream
                .map(campaign -> modelMapper.map(campaign, CampaignResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public CampaignResponse updateCampaign(Integer id, CampaignRequest request) {
        Campaign campaign = campaignRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Campaign not found with id: " + id));
        
        User creator = userRepository.findById(request.getCreatorUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + request.getCreatorUserId()));

        campaign.setCampaignName(request.getCampaignName());
        campaign.setIsActive(request.getIsActive());
        campaign.setCreatorUser(creator);

        Campaign updatedCampaign = campaignRepository.save(campaign);
        return modelMapper.map(updatedCampaign, CampaignResponse.class);
    }

    @Override
    public void deleteCampaign(Integer id) {
        if (!campaignRepository.existsById(id)) {
            throw new ResourceNotFoundException("Campaign not found with id: " + id);
        }
        campaignRepository.deleteById(id);
    }
}