package com.example.Marketing.service;

import com.example.Marketing.dto.NotificationChannelRequest;
import com.example.Marketing.dto.NotificationChannelResponse;
import java.util.List;

public interface NotificationChannelService {
    NotificationChannelResponse createChannel(NotificationChannelRequest request);
    NotificationChannelResponse getChannelById(Integer id);
    List<NotificationChannelResponse> getAllChannels();
    NotificationChannelResponse updateChannel(Integer id, NotificationChannelRequest request);
    void deleteChannel(Integer id);
}