package com.example.model;

/**
 * Created by Solbon on 2017-05-10.
 */
public class Circle {

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
