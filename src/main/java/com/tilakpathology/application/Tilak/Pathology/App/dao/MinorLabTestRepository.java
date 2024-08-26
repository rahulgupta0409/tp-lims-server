package com.tilakpathology.application.Tilak.Pathology.App.dao;

import com.tilakpathology.application.Tilak.Pathology.App.model.MajorLabTest;
import com.tilakpathology.application.Tilak.Pathology.App.model.MinorLabTest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface MinorLabTestRepository extends MongoRepository<MinorLabTest, BigInteger> {

    @Query("{ 'testId' : ?0 }")
    MinorLabTest getTestByTestId(String testId);


    //@Query("{ 'testId' : ?0 }")
    @Query(value = "{ 'testId': ?0 }, { '$set': { 'testName': ?1, 'testPrice': ?2, 'remarks': ?3 } }")
    void updateTestByTestId(String testId, String testName,Float testPrice,String remarks);

    @Query("{ 'testId' : { $in: ?0 } }")
    List<MinorLabTest> findAllMinorByTestId(Set<String> testIds);
}
