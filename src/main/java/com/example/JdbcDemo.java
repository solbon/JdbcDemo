package com.example;

import com.example.dao.JdbcDaoImpl;
import com.example.model.Circle;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Solbon on 2017-05-10.
 */
public class JdbcDemo {

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        JdbcDaoImpl dao = ctx.getBean("jdbcDaoImpl", JdbcDaoImpl.class);
//        Circle circle = dao.getCircle(1);
//        System.out.println(circle.getName());

        System.out.println(dao.getCircleCount());
        System.out.println(dao.getCircleName(1));
        System.out.println(dao.getCircleForId(1).getName());
        System.out.println(dao.getAllCircles().get(0).getName());
    }
}
