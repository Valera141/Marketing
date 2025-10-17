package com.example.Marketing.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import java.time.OffsetDateTime;

@Data
@Builder
public class ProcessingQueueResponse {

    @JsonProperty("queue_id")
    private Integer queueId;

    @JsonProperty("job_type")
    private String jobType;

    @JsonProperty("resource_fk_id")
    private Integer resourceFkId;

    private String status;
    private Integer attempts;

    @JsonProperty("creation_date")
    private OffsetDateTime creationDate;

    @JsonProperty("last_attempt")
    private OffsetDateTime lastAttempt;

    @JsonProperty("error_message")
    private String errorMessage;
}