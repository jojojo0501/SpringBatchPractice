package com.example.BatchCsvImport.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeJdbcRepository {

    @Autowired
    private JdbcTemplate jdbc;

    private static final String EXIST_SQL =
            "SELECT exists(select * from employee where id = ?)";

    public boolean exists(Integer id){
        boolean result = jdbc.queryForObject(EXIST_SQL,Boolean.class,id);
        return result;
    }

}
