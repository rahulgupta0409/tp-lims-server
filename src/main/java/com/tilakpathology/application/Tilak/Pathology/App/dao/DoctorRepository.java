package com.tilakpathology.application.Tilak.Pathology.App.dao;

import com.tilakpathology.application.Tilak.Pathology.App.model.Doctors;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface DoctorRepository extends MongoRepository<Doctors, BigInteger> {
}
