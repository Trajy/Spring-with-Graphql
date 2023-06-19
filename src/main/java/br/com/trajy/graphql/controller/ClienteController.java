package br.com.trajy.graphql.controller;

import br.com.trajy.graphql.model.Cliente;
import br.com.trajy.graphql.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@SchemaMapping(typeName = "ClienteController")
public class ClienteController extends Generalization {

    private final ClienteRepository repository;

    @SchemaMapping
    public Cliente getCliente(@Argument Long id) {
        return repository.findById(id).orElse(null);
    }

}
