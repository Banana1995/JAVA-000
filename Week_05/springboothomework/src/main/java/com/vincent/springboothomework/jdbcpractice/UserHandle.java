package com.vincent.springboothomework.jdbcpractice;

import com.vincent.springboothomework.model.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Service
public class UserHandle {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insert(People people) throws SQLException {

        DataSource dataSource = jdbcTemplate.getDataSource();
        Connection connection = dataSource.getConnection();
        String sql = "insert into peopledb.userinfo(people_name,age,skin_color,habit) VALUES(?,?,?,?)";

        jdbcTemplate.update(sql, people.getName(), people.getAge(), people.getSkinColor(), people.getHabit());
        System.out.println("inert over");

    }


}
