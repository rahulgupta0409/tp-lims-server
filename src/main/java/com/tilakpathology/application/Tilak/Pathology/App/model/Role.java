package com.tilakpathology.application.Tilak.Pathology.App.model;

import com.tilakpathology.application.Tilak.Pathology.App.model.Enums.UserRole;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "roles")
public class Role {

    @Id
    private String id;

    private UserRole name;

    public Role() {

    }

    public Role(UserRole name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserRole getName() {
        return name;
    }

    public void setName(UserRole name) {
        this.name = name;
    }
}
