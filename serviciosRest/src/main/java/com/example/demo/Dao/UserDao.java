package com.example.demo.Dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserDao {

    @Autowired
    @Qualifier("jdbcMaster")
    private JdbcTemplate jdbcTemplate;
 
    public Object findAll() {
    	return jdbcTemplate.queryForList("select * from users");
//        return jdbcTemplate.query("select * from user", new RowMapper<User>() {
//            @Override
//            public User mapRow(ResultSet rs, int arg1) throws SQLException {
//                return new User(rs.getInt("USER_ID"), rs.getString("USERNAME"), rs.getString("PASSWORD"));
//            }
//        });
    }

}