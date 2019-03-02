package com.example.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Config {

    @Bean
    public PropertySourcesPlaceholderConfigurer propertiesMysql() {
        log.info("mysql properties set");
        return setPropertySourcesPlaceholder("mysql.yml");
    }

    public PropertySourcesPlaceholderConfigurer setPropertySourcesPlaceholder(String ymlFileName) {
        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
        yaml.setResources(new ClassPathResource(ymlFileName));
        propertySourcesPlaceholderConfigurer.setProperties(yaml.getObject());
        log.info("YAML : {}", yaml.getObject());
        return propertySourcesPlaceholderConfigurer;
    }
}
