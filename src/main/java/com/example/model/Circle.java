package com.example.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Solbon on 2017-05-10.
 */
@Entity
public class Circle {
    @Id
    private int id;
    private String name;

    public Circle(int id, String name) {
        setId(id);
        setName(name);
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
}
