package com.example.dao;

import com.example.model.Circle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by Solbon on 2017-05-10.
 */
@Component
public class JdbcDaoImpl {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public Circle getCircle(int circleId) {
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
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

    public int getCircleCount() {
        return jdbcTemplate.queryForObject("select count(*) from circle", Integer.class);
    }

    public String getCircleName(int circleId) {
        String sql = "select name from circle where id = ?";
        return jdbcTemplate.queryForObject(sql, String.class, 1);
    }

    public Circle getCircleForId(int circleId) {
        String sql = "select * from circle where id = ?";
        return jdbcTemplate.queryForObject(sql, new CircleMapper(), 1);
    }

    public List<Circle> getAllCircles() {
        String sql = "select * from circle";
        return jdbcTemplate.query(sql, new CircleMapper());
    }

//    public void insertCircle(Circle circle) {
//        String sql = "insert into circle (id, name) values (?, ?)";
//        jdbcTemplate.update(sql, circle.getId(), circle.getName());
//    }

    public void insertCircle(Circle circle) {
        String sql = "insert into circle (id, name) values (:id, :name)";
        SqlParameterSource namedParameters = new MapSqlParameterSource("id", circle.getId())
                .addValue("name", circle.getName());
        namedParameterJdbcTemplate.update(sql, namedParameters);
    }

    public void createTriangleTable() {
        String sql = "create table triangle (id integer, name varchar(50))";
        jdbcTemplate.execute(sql);
    }

    private static final class CircleMapper implements RowMapper<Circle> {

        @Override
        public Circle mapRow(ResultSet rs, int rowNum) throws SQLException {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            Circle circle = new Circle(id, name);
            return circle;
        }
    }

}
