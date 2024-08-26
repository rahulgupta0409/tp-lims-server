package com.tilakpathology.application.Tilak.Pathology.App.dao;

import com.tilakpathology.application.Tilak.Pathology.App.model.MajorLabTest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Set;

@Repository
public interface MajorLabTestRepository extends MongoRepository<MajorLabTest, BigInteger> {

    @Query("{ 'majorTestId' : ?0 }")
    MajorLabTest getMajorTestByTestId(String majorLabTestId);


    @Query("{ 'majorTestId' : { $in: ?0 } }")
    List<MajorLabTest> findAllMajorByTestId(Set<String> testIds);
}
