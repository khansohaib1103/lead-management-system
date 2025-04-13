package com.leadmanagement.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class LeadResponse {
    private UUID id;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String companyName;
    private String notes;
    private LocalDateTime createdAt;
} 