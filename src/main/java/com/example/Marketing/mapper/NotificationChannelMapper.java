package com.example.Marketing.mapper;

import com.example.Marketing.dto.NotificationChannelRequest;
import com.example.Marketing.dto.NotificationChannelResponse;
import com.example.Marketing.model.NotificationChannel;

public class NotificationChannelMapper {

    /**
     * Convierte una entidad NotificationChannel a un NotificationChannelResponse DTO.
     */
    public static NotificationChannelResponse toResponse(NotificationChannel channel) {
        if (channel == null) {
            return null;
        }

        NotificationChannelResponse response = new NotificationChannelResponse();
        response.setChannelId(channel.getChannelId());
        response.setChannelType(channel.getChannelType());
        response.setConfiguration(channel.getConfiguration());
        response.setName(channel.getName());
        return response;
    }

    /**
     * Convierte un NotificationChannelRequest DTO a una entidad NotificationChannel.
     */
    public static NotificationChannel toEntity(NotificationChannelRequest request) {
        if (request == null) {
            return null;
        }

        NotificationChannel channel = new NotificationChannel();
        channel.setChannelType(request.getChannelType());
        channel.setConfiguration(request.getConfiguration());
        channel.setName(request.getName());
        return channel;
    }
}