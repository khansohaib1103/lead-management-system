package com.leadmanagement.service.impl;

import com.leadmanagement.dto.LeadRequest;
import com.leadmanagement.dto.LeadResponse;
import com.leadmanagement.entity.Lead;
import com.leadmanagement.exception.ResourceNotFoundException;
import com.leadmanagement.repository.LeadRepository;
import com.leadmanagement.service.LeadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LeadServiceImpl implements LeadService {

    private final LeadRepository leadRepository;

    @Override
    @Transactional
    public LeadResponse createLead(LeadRequest request) {

        Lead lead = new Lead();
        lead.setFullName(request.getFullName());
        lead.setEmail(request.getEmail());
        lead.setPhoneNumber(request.getPhoneNumber());
        lead.setCompanyName(request.getCompanyName());
        lead.setNotes(request.getNotes());

        Lead savedLead = leadRepository.save(lead);
        return mapToResponse(savedLead);
    }

    @Override
    @Transactional(readOnly = true)
    public List<LeadResponse> getAllLeads() {
        return leadRepository.findAll().stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    @Transactional
    public void deleteLead(UUID id) {
        if (!leadRepository.existsById(id)) {
            throw new ResourceNotFoundException("Lead with id " + id + " not found");
        }
        leadRepository.deleteById(id);
    }

    private LeadResponse mapToResponse(Lead lead) {
        LeadResponse response = new LeadResponse();
        response.setId(lead.getId());
        response.setFullName(lead.getFullName());
        response.setEmail(lead.getEmail());
        response.setPhoneNumber(lead.getPhoneNumber());
        response.setCompanyName(lead.getCompanyName());
        response.setNotes(lead.getNotes());
        response.setCreatedAt(lead.getCreatedAt());
        return response;
    }
} 