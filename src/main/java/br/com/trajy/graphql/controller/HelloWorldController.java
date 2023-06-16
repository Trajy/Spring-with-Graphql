package br.com.trajy.graphql.controller;

import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @QueryMapping
    public String helloWorld() {
        return ResponseEntity.ok("hello GraphQl").getBody();
    }
}
