package br.com.trajy.graphql.controller;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;

@DgsComponent
public class HelloWorldController {

    @DgsQuery
    public String helloWorld() {
        return "hello GraphQl";
    }
}
