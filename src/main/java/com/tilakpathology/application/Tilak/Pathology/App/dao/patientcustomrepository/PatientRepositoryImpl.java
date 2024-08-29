package com.tilakpathology.application.Tilak.Pathology.App.dao.patientcustomrepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.List;

public class PatientRepositoryImpl implements PatientRepositoryCustom{
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<PatientResult> findPatientsWithOrgNames() {
//        Criteria dateCriteria = Criteria.where("createdDate").gte(startDate).lte(endDate);
        Aggregation aggregation = Aggregation.newAggregation(
//                Aggregation.match(dateCriteria),
                Aggregation.lookup("organizations", "org.orgId", "orgId", "orgDetails"),
                Aggregation.unwind("orgDetails", true),
                Aggregation.lookup("doctors", "referredDoctor.doctorId", "doctorId", "doctorDetails"),
                Aggregation.unwind("doctorDetails", true),
                Aggregation.project("patientId", "firstName", "lastName", "age", "phoneNumber", "emailId")
                        .and("orgDetails.orgName").as("orgName")
                        .and("doctorDetails.doctorName").as("doctorName")
        );

        AggregationResults<PatientResult> results = mongoTemplate.aggregate(aggregation, "patients", PatientResult.class);
        return results.getMappedResults();
    }
}
