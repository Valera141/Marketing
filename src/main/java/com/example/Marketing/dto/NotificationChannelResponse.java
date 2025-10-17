package com.example.Marketing.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NotificationChannelResponse {

    private Integer channelId;
    private String channelType;
    private String configuration;
    private String name;
}