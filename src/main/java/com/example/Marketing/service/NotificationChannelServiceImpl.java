package com.example.Marketing.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.Marketing.dto.NotificationChannelRequest;
import com.example.Marketing.dto.NotificationChannelResponse;
import com.example.Marketing.exception.ResourceNotFoundException;
import com.example.Marketing.model.NotificationChannel;
import com.example.Marketing.repository.NotificationChannelRepository;

@Service
public class NotificationChannelServiceImpl implements NotificationChannelService {

    private final NotificationChannelRepository channelRepository;

    public NotificationChannelServiceImpl(NotificationChannelRepository channelRepository) {
        this.channelRepository = channelRepository;
    }

    @Override
    public NotificationChannelResponse createChannel(NotificationChannelRequest request) {
        NotificationChannel channel = new NotificationChannel();
        channel.setChannelType(request.getChannelType());
        channel.setConfiguration(request.getConfiguration());
        channel.setName(request.getName());

        NotificationChannel savedChannel = channelRepository.save(channel);
        return mapToResponse(savedChannel);
    }

    @Override
    public NotificationChannelResponse getChannelById(Integer id) {
        NotificationChannel channel = channelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Channel not found with id: " + id));
        return mapToResponse(channel);
    }

    @Override
    public List<NotificationChannelResponse> getAllChannels() {
        return channelRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public NotificationChannelResponse updateChannel(Integer id, NotificationChannelRequest request) {
        NotificationChannel channel = channelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Channel not found with id: " + id));

        channel.setChannelType(request.getChannelType());
        channel.setConfiguration(request.getConfiguration());
        channel.setName(request.getName());

        NotificationChannel updatedChannel = channelRepository.save(channel);
        return mapToResponse(updatedChannel);
    }

    @Override
    public void deleteChannel(Integer id) {
        if (!channelRepository.existsById(id)) {
            throw new ResourceNotFoundException("Channel not found with id: " + id);
        }
        channelRepository.deleteById(id);
    }

    private NotificationChannelResponse mapToResponse(NotificationChannel channel) {
        NotificationChannelResponse response = new NotificationChannelResponse();
        response.setChannelId(channel.getChannelId());
        response.setChannelType(channel.getChannelType());
        response.setConfiguration(channel.getConfiguration());
        response.setName(channel.getName());
        return response;
    }
}