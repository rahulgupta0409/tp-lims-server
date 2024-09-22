package com.tilakpathology.application.Tilak.Pathology.App.service.impl;

import com.tilakpathology.application.Tilak.Pathology.App.dao.*;
import com.tilakpathology.application.Tilak.Pathology.App.dao.patientcustomrepository.PatientResult;
import com.tilakpathology.application.Tilak.Pathology.App.dto.PatientDto;
import com.tilakpathology.application.Tilak.Pathology.App.dto.response.PatientResponseDto;
import com.tilakpathology.application.Tilak.Pathology.App.exceptions.type.BadRequestException;
import com.tilakpathology.application.Tilak.Pathology.App.model.*;
import com.tilakpathology.application.Tilak.Pathology.App.model.helpermodel.Doctor;
import com.tilakpathology.application.Tilak.Pathology.App.model.helpermodel.MinorTest;
import com.tilakpathology.application.Tilak.Pathology.App.model.helpermodel.Org;
import com.tilakpathology.application.Tilak.Pathology.App.model.helpermodel.Tests;
import com.tilakpathology.application.Tilak.Pathology.App.service.PatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static com.tilakpathology.application.Tilak.Pathology.App.constants.IntegerConstants.SEVEN;

@Service
public class PatientServiceImpl implements PatientService {

    private final Logger log = LoggerFactory.getLogger(PatientServiceImpl.class);

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private MajorLabTestRepository majorLabTestRepository;

    @Autowired
    private MinorLabTestRepository minorLabTestRepository;

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private DoctorRepository doctorRepository;



    @Override
    @Async("taskExecutor")
    public CompletableFuture<PatientResponseDto> addPatient(PatientDto patientDto) throws BadRequestException {
        CompletableFuture<List<Tests>> majorTestsFuture = getAllMajorTests(patientDto.getLabTestIds());
        CompletableFuture<List<Tests>> minorTestsFuture = getAllMinorTests(patientDto.getLabTestIds());
        CompletableFuture<Org> organizationFuture = getOrganization(patientDto.getOrgId());
        CompletableFuture<Doctor> doctorFuture = getDoctor(patientDto.getDoctorId());

        return CompletableFuture.allOf(majorTestsFuture, minorTestsFuture, organizationFuture)
                .thenApplyAsync(v -> {
                    try {
                        List<Tests> majorTests = majorTestsFuture.get();
                        List<Tests> minorTests = minorTestsFuture.get();
                        Org organization = organizationFuture.get();
                        Doctor doctor = doctorFuture.get();
                        List<Tests> allTests = new ArrayList<>();
                        allTests.addAll(majorTests);
                        allTests.addAll(minorTests);

                        Patient patient = Patient.builder()
                                .patientId(UUID.randomUUID() + UUID.randomUUID().toString().substring(SEVEN))
                                .firstName(patientDto.getFirstName())
                                .lastName(patientDto.getLastName())
                                .age(patientDto.getAge())
                                .phoneNumber(patientDto.getPhoneNumber())
                                .emailId(patientDto.getEmailId())
                                .tests(allTests)
                                .org(organization)
                                .referredDoctor(doctor)
                                .isUpi(patientDto.getIsUpi())
                                .isOutSampled(patientDto.getIsOutSampled())
                                .discount(patientDto.getDiscount())
                                .dueAmount(patientDto.getDueAmount())
                                .totalAmount(patientDto.getTotalAmount())
                                .paidAmount(patientDto.getPaidAmount())
                                .createdDate(LocalDateTime.now().toString())
                                .createdBy(patientDto.getCreatedBy())
                                .build();

                        patientRepository.save(patient);
                        log.info("Patient Successfully Created With PatientId {}", patient.getPatientId());

                        return PatientResponseDto.builder()
                                .patientId(patient.getPatientId())
                                .firstName(patient.getFirstName())
                                .lastName(patient.getLastName())
                                .age(patient.getAge())
                                .phoneNumber(patient.getPhoneNumber())
                                .emailId(patient.getEmailId())
                                .createdDate(patient.getCreatedDate())
                                .build();
                    } catch (Exception e) {
                        log.error("Error processing patient data", e);
                        throw new RuntimeException("Error processing patient data", e);
                    }
                });
    }


    private CompletableFuture<List<Tests>> getAllMajorTests(Set<String> testIds) {
        return CompletableFuture.supplyAsync(() -> {
            List<MajorLabTest> allMajorTests = majorLabTestRepository.findAllMajorByTestId(testIds);
            return allMajorTests.stream()
                    .map(test -> {
                        Tests tests = new Tests();
                        tests.setTestId(test.getMajorTestId());
                        tests.setTestName(test.getMajorTestName());
                        tests.setRemarks(test.getMajorTestRemarks());
                        tests.setIsMajorLabTest(true);
                        List<MinorTest> minorTestList = test.getMinorLabTestList().stream()
                                .map(minorLabTest -> {
                                    MinorTest minorTest = new MinorTest();
                                    minorTest.setTestId(minorLabTest.getTestId());
                                    minorTest.setTestName(minorLabTest.getTestName());
                                    minorTest.setRemarks(minorLabTest.getRemarks());
                                    return minorTest;
                                }).collect(Collectors.toList());
                        tests.setMinorLabTestList(minorTestList);
                        return tests;
                    })
                    .collect(Collectors.toList());
        });
    }

    private CompletableFuture<List<Tests>> getAllMinorTests(Set<String> testIds) {
        return CompletableFuture.supplyAsync(() -> {
            List<MinorLabTest> allMinorTests = minorLabTestRepository.findAllMinorByTestId(testIds);
            return allMinorTests.stream()
                    .map(test -> {
                        Tests tests = new Tests();
                        tests.setTestId(test.getTestId());
                        tests.setTestName(test.getTestName());
                        tests.setRemarks(test.getRemarks());
                        return tests;
                    })
                    .collect(Collectors.toList());
        });
    }

    private CompletableFuture<Org> getOrganization(String orgId) {
        return CompletableFuture.supplyAsync(() -> {
            Organization organization = organizationRepository.findByOrganizationId(orgId);
            return (organization != null) ? Org.builder()
                    .orgId(organization.getOrgId())
                    .build() : null;
        });
    }

    private CompletableFuture<Doctor> getDoctor(String doctorId) {
        return CompletableFuture.supplyAsync(() -> {
            Doctors doctors = doctorRepository.findDoctorByDoctorId(doctorId);
            return (doctors!=null) ? Doctor.builder()
                    .doctorId(doctors.getDoctorId())
                    .build() : null;
        });
    }

    @Override
    public List<?> getAllPatients() {
        List<PatientResult> patientResultList = patientRepository.findPatientsWithOrgNames();
        return (patientResultList != null) ? patientResultList : null;
    }

    @Override
    public List<?> getPatientsByStartEndDate(String startDate, String endDate) {
        List<Patient> patientResultList  = patientRepository.findAllPatientsBetweenDateTime(startDate, endDate);
        return patientResultList;
    }

}
