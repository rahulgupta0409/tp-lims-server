package com.tilakpathology.application.Tilak.Pathology.App.dao;

import com.tilakpathology.application.Tilak.Pathology.App.model.Organization;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface OrganizationRepository extends MongoRepository<Organization, BigInteger> {

    @Query("{ 'orgId' : ?0 }")
    Organization findByOrganizationId(String organizationId);
}
