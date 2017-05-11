package com.example.dao;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

/**
 * Created by Solbon on 2017-05-11.
 */
public class NamedParameterJdbcDaoImpl extends NamedParameterJdbcDaoSupport {

    public int getCircleCount() {
        String sql = "select count(*) from circle";
        return this.getJdbcTemplate().queryForObject(sql, Integer.class);
    }
}
