package com.tilakpathology.application.Tilak.Pathology.App.dao;

import com.tilakpathology.application.Tilak.Pathology.App.model.MinorLabTest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface MinorLabTestRepository extends MongoRepository<MinorLabTest, BigInteger> {

    @Query("{ 'testId' : ?0 }")
    Optional<MinorLabTest> getTestByTestId(String testId);
}
