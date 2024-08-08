package com.tilakpathology.application.Tilak.Pathology.App.service.impl;

import com.tilakpathology.application.Tilak.Pathology.App.config.mailservice.model.Mail;
import com.tilakpathology.application.Tilak.Pathology.App.config.mailservice.service.MailService;
import com.tilakpathology.application.Tilak.Pathology.App.dao.OrganizationRepository;
import com.tilakpathology.application.Tilak.Pathology.App.dto.OrganizationDto;
import com.tilakpathology.application.Tilak.Pathology.App.model.Organization;
import com.tilakpathology.application.Tilak.Pathology.App.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.tilakpathology.application.Tilak.Pathology.App.constants.IntegerConstants.SEVEN;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private MailService mailService;


    @Override
    public Organization createOrganization(OrganizationDto organizationDto) {

        Organization organization = Organization.builder()
                .orgId(UUID.randomUUID() + UUID.randomUUID().toString().substring(SEVEN))
                .orgName(organizationDto.getOrgName())
                .orgDetails(organizationDto.getOrgDetails())
                .build();
        organizationRepository.save(organization);

//        Mail mail = new Mail();
//        mail.setMailFrom("rahulguptaharsh081218@gmail.com");
//        mail.setMailTo("himanshu.path@gmail.com");
//        mail.setMailContent("Kya be kya haal?");
//        mail.setMailSubject("Your Organization is registered..");
//        mailService.sendEmail(mail);
        return organization;
    }

    @Override
    public List<Organization> getOrganizations() {
        List<Organization> organization = organizationRepository.findAll();
        return organization;
    }

    @Override
    public Organization getOrganizationById(String organizationId) {
        Organization organization = organizationRepository.findByOrganizationId(organizationId);
        if(organization != null){
            return organization;
        }
        return null;
    }

    @Override
    public void updateOrganization(OrganizationDto organizationDto) {

    }

    @Override
    public void deleteOrganization(String organizationId) {

    }
}
