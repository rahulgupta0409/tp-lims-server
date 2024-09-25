package com.tilakpathology.application.Tilak.Pathology.App.dao;

import com.tilakpathology.application.Tilak.Pathology.App.dao.patientcustomrepository.PatientRepositoryCustom;
import com.tilakpathology.application.Tilak.Pathology.App.model.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface PatientRepository extends MongoRepository<Patient, BigInteger> , PatientRepositoryCustom {

    @Query("{'patientId':?0}")
    Patient findPatientByPatientId(String patientId);

    @Query("{'createdDate':{$gte: ?0, $lte: ?1}}")
    List<Patient> findAllPatientsBetweenDateTime(String startDate, String endDate);


    @Query("{ '$text': { '$search': ?0 } }")
    List<Patient> findPatientsBySearch(String searchItem);

    @Query("{'createdDate':{$gte:?0}}")
    List<Patient> findPatientLabIdByCurrentDate(String createdDate);



}
