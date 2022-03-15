package com.example.k8s.http;


import com.example.k8s.config.ParamProperties;
import com.example.k8s.service.ConfigMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.MessageFormat;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class ExampleController {

    private final ParamProperties properties;
    private final ConfigMap configMap;

    @GetMapping(path = "/secret")
    public String getSecret() {

        String user = properties.getUser();
        String password = properties.getPassword();

        String message = MessageFormat.format("<h1>User: {0}. Password: {1}</h1>", user, password);

        return message;
    }

    @GetMapping(path = "/myfamily")
    public String getFamily() {

        String message = null;
        try {
            message = MessageFormat.format("<h1>My family:{0}</h1>", configMap.readFile());
        } catch (IOException e) {
           e.printStackTrace();
        }

        return message;
    }

    @GetMapping
    public String get() {
        String name = properties.getName();
        String age = properties.getAge();

        String message = MessageFormat.format("<h1>Hello, I''m {0}. I''m {1}</h1>", name, age);

        return message;
    }
}
