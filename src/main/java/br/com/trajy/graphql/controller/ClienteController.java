package br.com.trajy.graphql.controller;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.util.Objects.nonNull;

import br.com.trajy.graphql.model.input.ClienteInput;
import br.com.trajy.graphql.model.output.Cliente;
import br.com.trajy.graphql.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteRepository repository;

    public Cliente findById(Long id) {
        return this.repository.findById(id).orElse(null);
    }

    public Long save(ClienteInput cliente) {
        return this.repository.save(cliente).getId();
    }

    public Cliente update(ClienteInput cliente) {
        return this.repository.save(cliente);
    }

    public Boolean delete(Long id) {
        Cliente cliente = this.findById(id);
        if(nonNull(cliente)) {
            this.repository.delete(cliente);
            return TRUE;
        }
        return FALSE;
    }

}
