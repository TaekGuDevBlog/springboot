package com.example.demo.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "mysql")
public class MysqlConfig {
    private String ip;
    private int port;
    private String id;
    private String pw;
    private String schema;
}
