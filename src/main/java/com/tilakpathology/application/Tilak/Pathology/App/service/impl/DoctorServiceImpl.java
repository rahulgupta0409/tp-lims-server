package com.tilakpathology.application.Tilak.Pathology.App.service.impl;

import com.tilakpathology.application.Tilak.Pathology.App.dao.DoctorRepository;
import com.tilakpathology.application.Tilak.Pathology.App.dto.DoctorDto;
import com.tilakpathology.application.Tilak.Pathology.App.exceptions.type.BadRequestException;
import com.tilakpathology.application.Tilak.Pathology.App.model.Doctors;
import com.tilakpathology.application.Tilak.Pathology.App.service.DoctorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static com.tilakpathology.application.Tilak.Pathology.App.constants.IntegerConstants.SEVEN;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final Logger log = LoggerFactory.getLogger(DoctorServiceImpl.class);

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public Doctors createDoctor(DoctorDto doctorDto) throws BadRequestException {
        try {
             Doctors doctors = Doctors.builder()
                    .doctorId(UUID.randomUUID() + UUID.randomUUID().toString().substring(SEVEN))
                    .doctorName(doctorDto.getDoctorName())
                    .doctorDetails(doctorDto.getDoctorDetails())
                     .orgId(doctorDto.getOrgId())
                    .createdBy(doctorDto.getCreatedBy())
                    .createdDate(LocalDateTime.now().toString())
                    .build();
            doctorRepository.save(doctors);
            return doctors;
        }catch (BadRequestException ex){
            throw new BadRequestException("Please give a right doctor entity");
        }
    }

    @Override
    public List<Doctors> getAllDoctors() {
        List<Doctors> doctorsList = doctorRepository.findAll();
        return (doctorsList != null) ? doctorsList : null;
    }

    @Override
    public Doctors getDoctorById(String doctorId) {
        Doctors doctors = doctorRepository.findDoctorByDoctorId(doctorId);
        return (doctors != null) ? doctors : null;
    }

    @Override
    public Doctors getDoctorByOrganizationId(String organizationId) {
        return null;
    }

    @Override
    public void updateDoctor(DoctorDto doctorDto) {

    }

    @Override
    public void deleteDoctor(String doctorId) {

    }
}
