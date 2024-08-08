package com.tilakpathology.application.Tilak.Pathology.App.service;

import com.tilakpathology.application.Tilak.Pathology.App.dto.OrganizationDto;
import com.tilakpathology.application.Tilak.Pathology.App.model.Organization;

import java.util.List;

public interface OrganizationService {

    Organization createOrganization(OrganizationDto organizationDto);

    List<Organization> getOrganizations();

    Organization getOrganizationById(String organizationId);

    void updateOrganization(OrganizationDto organizationDto);

    void deleteOrganization(String organizationId);
}
