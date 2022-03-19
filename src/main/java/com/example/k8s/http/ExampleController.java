package com.example.k8s.http;


import com.example.k8s.config.ParamProperties;
import com.example.k8s.service.ConfigMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.text.MessageFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class ExampleController {

    private final ParamProperties properties;
    private final ConfigMap configMap;
    private LocalDateTime startedAt = LocalDateTime.now();


    @GetMapping(path = "/healthz")
    public String getHealthz(HttpServletResponse response) {

        var duration = Duration.between(startedAt, LocalDateTime.now());

        if(duration.getSeconds() < 10) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return MessageFormat.format("<h1>Duration: {0}</h1>", duration.getSeconds());
        }
        response.setStatus(HttpStatus.OK.value());
        return "OK";
    }

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
