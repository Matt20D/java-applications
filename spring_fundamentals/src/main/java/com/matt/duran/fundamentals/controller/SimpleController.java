package com.matt.duran.fundamentals.controller;

import com.matt.duran.fundamentals.entity.Application;
import com.matt.duran.fundamentals.exception.ApplicationNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class SimpleController {

    @Value("${spring.application.name}")
    String appName;

    @GetMapping("/")
    public ResponseEntity<String> getHomePage() {

        return new ResponseEntity<String>(appName, HttpStatus.OK);
    }

    @GetMapping("/greeting/{name}")
    public ResponseEntity<String> customGreeting(@PathVariable("name") String name) {

        return new ResponseEntity<String>("Hello my new friend --> " + name, HttpStatus.OK);
    }

}
