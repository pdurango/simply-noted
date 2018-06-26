package com.silvesla.simplynoted;

import java.io.Serializable;

public class Task implements Serializable
{
    private int id;
    private String name;
    private String dateCreated;


    public Task(int id, String name, String dateCreated)
    {
        this.id = id;
        this.name = name;
        this.dateCreated = dateCreated;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }
}
