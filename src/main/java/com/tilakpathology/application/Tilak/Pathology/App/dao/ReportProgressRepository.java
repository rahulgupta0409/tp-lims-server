package com.tilakpathology.application.Tilak.Pathology.App.dao;

import com.tilakpathology.application.Tilak.Pathology.App.model.ReportProgress;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface ReportProgressRepository extends MongoRepository<ReportProgress, BigInteger> {

    @Query("{ 'patientId': { $in: ?0 } }")
    List<ReportProgress> findReportProgressByPatientIds(List<String> patientIds);

    @Query("{'patientId':?0}")
    ReportProgress findByPatientId(String patientId);

}
