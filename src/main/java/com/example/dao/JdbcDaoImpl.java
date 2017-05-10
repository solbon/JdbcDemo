package com.example.dao;

import com.example.model.Circle;

import java.sql.*;

/**
 * Created by Solbon on 2017-05-10.
 */
public class JdbcDaoImpl {

    public Circle getCircle(int circleId) {
        Connection conn = null;
        try {
            String driver = "org.apache.derby.jdbc.ClientDriver";
            Class.forName(driver);
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/db");
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM circle where id = ?");
            ps.setInt(1, circleId);

            Circle circle = null;
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                circle = new Circle(circleId, rs.getString("name"));
            }
            rs.close();
            ps.close();
            return circle;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) { }
        }
    }
}
