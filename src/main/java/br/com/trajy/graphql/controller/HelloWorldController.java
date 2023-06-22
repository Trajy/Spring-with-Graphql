package br.com.trajy.graphql.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    public String helloWorld() {
        return ResponseEntity.ok("hello GraphQl").getBody();
    }
}
