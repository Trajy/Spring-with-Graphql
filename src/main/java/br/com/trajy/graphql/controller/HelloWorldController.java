package br.com.trajy.graphql.controller;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@DgsComponent
public class HelloWorldController {

    @DgsQuery
    public String helloWorld() {
        return "hello GraphQl";
    }

    @DgsQuery
    public String headersTest(
            @RequestHeader(required = false) String optionalHeader,
            @RequestHeader String mandatoryHeader,
            @RequestParam(required = false) String optionalParam,
            @RequestParam String mandatoryParam
    ) {
        return "{ \"optionalHeader\": \"" + optionalHeader + "\", "
                + "\"mandatoryHeader\": \"" + mandatoryHeader + "\", "
                + "\"optionalParam\": \"" + optionalParam + "\", "
                + "\"mandatoryParam\": \"" + mandatoryParam + "\" }";
    }
}
