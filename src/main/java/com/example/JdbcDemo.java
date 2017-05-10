package com.example;

import com.example.dao.JdbcDaoImpl;
import com.example.model.Circle;

/**
 * Created by Solbon on 2017-05-10.
 */
public class JdbcDemo {

    public static void main(String[] args) {
        Circle circle = new JdbcDaoImpl().getCircle(1);
        System.out.println(circle.getName());
    }
}
