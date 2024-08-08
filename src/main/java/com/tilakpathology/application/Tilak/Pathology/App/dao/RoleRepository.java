package com.tilakpathology.application.Tilak.Pathology.App.dao;

import com.tilakpathology.application.Tilak.Pathology.App.model.Enums.UserRole;
import com.tilakpathology.application.Tilak.Pathology.App.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends MongoRepository<Role, String> {


    @Query("{ 'roleName' : ?0 }")
    Role findByName(UserRole name);
}
