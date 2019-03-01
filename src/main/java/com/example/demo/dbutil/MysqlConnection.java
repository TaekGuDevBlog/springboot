package com.example.demo.dbutil;

import com.example.demo.util.PropertyLoader;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Slf4j
public class MysqlConnection {
    private static final String PROPERTIES_FILE = "mysql.properties";
    private static final String mysqlClassForName = ("com.mysql.jdbc.Driver");

    private static String dbIp;
    private static int dbPort;
    private static String dbSchema;
    private static String dbId;
    private static String dbPw;

    public static Connection getConnection() {
        readMysqlDBMetaConfiguration();
        Connection connection = null;
        try {
            Class.forName(mysqlClassForName);
            String connectionString = String.format("jdbc:mysql://%s:%d/%s", dbIp, dbPort, dbSchema);
            connection = DriverManager.getConnection(connectionString, dbId, dbPw);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

    private static void readMysqlDBMetaConfiguration() {
        try {
            Configuration config = PropertyLoader.loadConfigFile(PROPERTIES_FILE);

            dbIp = (String) config.getProperty("ip");
            dbPort = (Integer) config.getProperty("port");
            dbSchema = (String) config.getProperty("schema");
            dbId = (String) config.getProperty("id");
            dbPw = (String) config.getProperty("pw");

        } catch (ConfigurationException e) {
            log.error("{}", e);
        }
    }
}
