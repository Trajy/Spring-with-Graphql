package br.com.trajy.graphql.controller;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.util.Objects.nonNull;

import br.com.trajy.graphql.model.input.ClienteInput;
import br.com.trajy.graphql.model.output.Cliente;
import br.com.trajy.graphql.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@SchemaMapping(typeName = "ClienteController")
public class ClienteController {

    private final ClienteRepository repository;

    @SchemaMapping
    public Cliente findById(@Argument Long id) {
        return this.repository.findById(id).orElse(null);
    }

    @SchemaMapping
    public Long save(@Argument ClienteInput cliente) {
        return this.repository.save(cliente).getId();
    }

    @SchemaMapping
    public Cliente update(@Argument ClienteInput cliente) {
        return this.repository.save(cliente);
    }

    @SchemaMapping
    public Boolean delete(@Argument Long id) {
        Cliente cliente = this.findById(id);
        if(nonNull(cliente)) {
            this.repository.delete(cliente);
            return TRUE;
        }
        return FALSE;
    }

}
