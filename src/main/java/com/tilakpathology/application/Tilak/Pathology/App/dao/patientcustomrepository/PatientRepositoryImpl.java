package com.tilakpathology.application.Tilak.Pathology.App.dao.patientcustomrepository;

import com.mongodb.ReadConcern;
import com.mongodb.ReadPreference;
import com.mongodb.TransactionOptions;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.FindOneAndReplaceOptions;
import com.tilakpathology.application.Tilak.Pathology.App.model.Patient;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.ReturnDocument.AFTER;


public class PatientRepositoryImpl implements PatientRepositoryCustom{

//    private static final TransactionOptions txnOptions = TransactionOptions.builder()
//            .readPreference(ReadPreference.primary())
//            .readConcern(ReadConcern.MAJORITY)
//            .writeConcern(WriteConcern.MAJORITY)
//            .build();
//    private final MongoClient client;
//    private MongoCollection<Patient> patientCollection;
//
//    public PatientRepositoryImpl(MongoClient mongoClient) {
//        this.client = mongoClient;
//    }
//
//    @PostConstruct
//    void init() {
//        patientCollection = client.getDatabase("tilak-pathology-db").getCollection("patients", Patient.class);
//    }
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

    @Override
    public void updateTestForPatient(Patient patient) {
        FindOneAndReplaceOptions options = new FindOneAndReplaceOptions().returnDocument(AFTER);
        Query query = new Query().addCriteria(Criteria.where("patientId").is(patient.getPatientId()));
        mongoTemplate.findAndReplace(query, patient);

    }
}
