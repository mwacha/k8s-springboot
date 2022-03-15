package com.example.k8s.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "params")
@Getter
@Setter
public class ParamProperties {
    private String name;
    private String age;
    private String user;
    private String password;
}
