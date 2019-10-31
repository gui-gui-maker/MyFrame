package com.edu.jdbc.repository.impl;

import com.edu.bean.User;
import com.edu.jdbc.repository.UserJDaoI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserJDao implements UserJDaoI {
    @Autowired
    private JdbcTemplate primaryJdbcTemplate;

    @Override
    public int save(User user,JdbcTemplate jdbcTemplate) {
        if(jdbcTemplate == null){
            jdbcTemplate= primaryJdbcTemplate;
        }
        return jdbcTemplate.update("INSERT INTO users(username, password, salt) values(?, ?, ?)",
              user.getUserName(), user.getPassword(), user.getSalt());
    }

    @Override
    public int update(User user,JdbcTemplate jdbcTemplate) {
        if(jdbcTemplate==null){
            jdbcTemplate= primaryJdbcTemplate;
        }
        return jdbcTemplate.update("UPDATE users SET name = ? , password = ? , age = ? WHERE id=?",
        		 user.getUserName(), user.getPassword(), user.getSalt(), user.getId());
    }

    @Override
    public int delete(long id,JdbcTemplate jdbcTemplate) {
        if(jdbcTemplate==null){
            jdbcTemplate= primaryJdbcTemplate;
        }
        return jdbcTemplate.update("DELETE FROM users where id = ? ",id);

    }

    @Override
    public User findById(long id,JdbcTemplate jdbcTemplate) {
        if(jdbcTemplate==null){
            jdbcTemplate= primaryJdbcTemplate;
        }
        return jdbcTemplate.queryForObject("SELECT * FROM users WHERE id=?", new Object[] { id }, new BeanPropertyRowMapper<User>(User.class));
    }

    @Override
    public List<User> findALL(JdbcTemplate jdbcTemplate) {
        if(jdbcTemplate==null){
            jdbcTemplate= primaryJdbcTemplate;
        }
        return jdbcTemplate.query("SELECT * FROM users", new UserRowMapper());
        // return jdbcTemplate.query("SELECT * FROM users", new BeanPropertyRowMapper(User.class));
    }

    class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getString("id"));
            user.setUserName(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setSalt(rs.getString("salt"));
            return user;
        }
    }

}
