package com.example.k8s.http;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class ExampleController {

    @GetMapping
    public String get() {
        return "<h1>Hello World!!!</h1>";
    }
}
