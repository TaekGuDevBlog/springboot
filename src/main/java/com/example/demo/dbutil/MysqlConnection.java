package com.example.demo.dbutil;

import com.example.demo.config.MysqlConfig;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Slf4j
public class MysqlConnection {
    public static Connection getConnection(MysqlConfig mysqlConfig) {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String connectionString = String.format("jdbc:mysql://%s:%d/%s?serverTimezone=UTC", mysqlConfig.getIp(), mysqlConfig.getPort(), mysqlConfig.getSchema());
            connection = DriverManager.getConnection(connectionString, mysqlConfig.getId(), mysqlConfig.getPw());
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
