package com.example.Marketing.controller;

import com.example.Marketing.dto.NotificationChannelRequest;
import com.example.Marketing.dto.NotificationChannelResponse;
import com.example.Marketing.service.NotificationChannelService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notification-channels")
public class NotificationChannelController {

    private final NotificationChannelService notificationChannelService;

    public NotificationChannelController(NotificationChannelService notificationChannelService) {
        this.notificationChannelService = notificationChannelService;
    }

    @PostMapping
    public ResponseEntity<NotificationChannelResponse> createChannel(
            @Valid @RequestBody NotificationChannelRequest request) {
        NotificationChannelResponse createdChannel = notificationChannelService.createChannel(request);
        return new ResponseEntity<>(createdChannel, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificationChannelResponse> getChannelById(@PathVariable Integer id) {
        NotificationChannelResponse channel = notificationChannelService.getChannelById(id);
        return ResponseEntity.ok(channel);
    }

    @GetMapping
    public ResponseEntity<List<NotificationChannelResponse>> getAllChannels() {
        List<NotificationChannelResponse> channels = notificationChannelService.getAllChannels();
        return ResponseEntity.ok(channels);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NotificationChannelResponse> updateChannel(
            @PathVariable Integer id,
            @Valid @RequestBody NotificationChannelRequest request) {
        NotificationChannelResponse updatedChannel = notificationChannelService.updateChannel(id, request);
        return ResponseEntity.ok(updatedChannel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChannel(@PathVariable Integer id) {
        notificationChannelService.deleteChannel(id);
        return ResponseEntity.noContent().build();
    }
}