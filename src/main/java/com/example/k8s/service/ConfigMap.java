package com.example.k8s.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Slf4j
@Service
public class ConfigMap {

    private final String PATH = "/java/myfamily/family.txt";

    public String readFile() throws IOException {
        return Files.readString(Paths.get(PATH));
    }
}
