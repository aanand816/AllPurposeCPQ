package com.allpurposecpq.backend.controller;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DbTestController {

    private final JdbcTemplate jdbc;

    public DbTestController(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @GetMapping("/db/ping")
    public String ping() {
        return jdbc.queryForObject("SELECT 'CONNECTED' FROM dual", String.class);
    }

    @GetMapping("/db/tables")
    public List<String> tables() {
        return jdbc.queryForList("SELECT table_name FROM user_tables ORDER BY table_name", String.class);
    }
}