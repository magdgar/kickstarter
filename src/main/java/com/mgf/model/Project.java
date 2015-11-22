package com.mgf.model;

import lombok.Data;
@Data
public class Project {
    String name;
    String description;
    int creatorID;
    int money = 15;

    public Project(String name) {
        this.name = name;
    }

    public Project( String name, String desc, int creatorID) {
        this.name = name;
        this.description = desc;
        this.creatorID = creatorID;
    }
}
