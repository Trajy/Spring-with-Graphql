package br.com.trajy.graphql.resolver;

import br.com.trajy.graphql.controller.ClienteController;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ClienteResolver {

    private final ClienteController controller;

    public ClienteController clienteController() {
        return controller;
    }

}
