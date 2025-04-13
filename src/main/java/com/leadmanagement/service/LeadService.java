package com.leadmanagement.service;

import com.leadmanagement.dto.LeadRequest;
import com.leadmanagement.dto.LeadResponse;
import java.util.List;
import java.util.UUID;

public interface LeadService {
    LeadResponse createLead(LeadRequest request);
    List<LeadResponse> getAllLeads();
    void deleteLead(UUID id);
} 