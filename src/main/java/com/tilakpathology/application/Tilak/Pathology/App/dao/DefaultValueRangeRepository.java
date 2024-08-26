package com.tilakpathology.application.Tilak.Pathology.App.dao;

import com.tilakpathology.application.Tilak.Pathology.App.model.DefaultValueRange;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface DefaultValueRangeRepository extends MongoRepository<DefaultValueRange, BigInteger> {
}
