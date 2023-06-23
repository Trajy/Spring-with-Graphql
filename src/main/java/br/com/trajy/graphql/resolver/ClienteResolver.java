package br.com.trajy.graphql.resolver;

import br.com.trajy.graphql.controller.ClienteController;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import lombok.RequiredArgsConstructor;

@DgsComponent
@RequiredArgsConstructor
public class ClienteResolver {

    private final ClienteController controller;

    @DgsQuery
    public ClienteController clienteController() {
        return controller;
    }

}
