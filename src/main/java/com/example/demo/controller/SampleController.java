package com.example.demo.controller;

import com.example.demo.dbutil.MysqlConnection;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.SQLException;

@RestController
public class SampleController {

    MysqlConnection mysqlConnection = new MysqlConnection();

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String testMapping() throws SQLException, ClassNotFoundException {
        Connection connection = mysqlConnection.getConnection();
        return "success";
    }

    @RequestMapping(value = "/test2", method = RequestMethod.GET)
    public String test2Mapping() {
        return "test";
    }

    @RequestMapping(value = "/test3", method = RequestMethod.GET)
    public String test3Mapping() {
        return "test";
    }

    @RequestMapping(value = "/test4", method = RequestMethod.GET)
    public String test4Mapping() {
        return "test";
    }
}
