package com.example;

import com.example.dao.JdbcDaoImpl;
import com.example.dao.NamedParameterJdbcDaoImpl;
import com.example.model.Circle;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Solbon on 2017-05-10.
 */
public class JdbcDemo {

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        NamedParameterJdbcDaoImpl dao = ctx.getBean("namedParameterJdbcDaoImpl", NamedParameterJdbcDaoImpl.class);
        System.out.println(dao.getCircleCount());
    }
}
