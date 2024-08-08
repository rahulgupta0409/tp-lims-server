package com.tilakpathology.application.Tilak.Pathology.App.controller;

import com.tilakpathology.application.Tilak.Pathology.App.dto.OrganizationDto;
import com.tilakpathology.application.Tilak.Pathology.App.exceptions.type.BadRequestException;
import com.tilakpathology.application.Tilak.Pathology.App.model.MinorLabTest;
import com.tilakpathology.application.Tilak.Pathology.App.model.Organization;
import com.tilakpathology.application.Tilak.Pathology.App.service.OrganizationService;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/organization")
@CrossOrigin(origins = "http://localhost:3000")
public class OrganizationController {

    private final Logger log = LoggerFactory.getLogger(OrganizationController.class);

    @Autowired
    private OrganizationService organizationService;

    @PostMapping(value = "/addOrganization")
    @Operation(summary = "Save the Organizations into the database.")
    public ResponseEntity<Organization> addOrganization(@RequestBody OrganizationDto organizationDto) {

        if(organizationDto.getOrgName() == null){
            log.info("Invalid Input about Organization : {}"
                    , organizationDto.getOrgName());
            throw new BadRequestException("Invalid Input about Organization");
        }
        Organization organization = organizationService.createOrganization(organizationDto);
        System.out.println(organization);

        return new ResponseEntity<>(organization, HttpStatus.CREATED);
    }

    @GetMapping(value = "/getAllOrganizations")
    @Operation(summary = "Fetching all Organizations.")
    public ResponseEntity<List<Organization>> getAllOrganizations() {
        List<Organization> organizations = organizationService.getOrganizations();
        return new ResponseEntity<>(organizations, HttpStatus.OK);
    }
}
