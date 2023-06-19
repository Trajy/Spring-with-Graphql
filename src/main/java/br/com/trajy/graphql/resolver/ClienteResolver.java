package br.com.trajy.graphql.resolver;

import br.com.trajy.graphql.controller.ClienteController;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClienteResolver {

    private final ClienteController controller;

    @QueryMapping
    public ClienteController clienteController() {
        return controller;
    }

}
