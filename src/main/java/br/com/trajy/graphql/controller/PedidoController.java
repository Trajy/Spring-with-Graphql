package br.com.trajy.graphql.controller;

import br.com.trajy.graphql.model.output.PedidoOutput;
import br.com.trajy.graphql.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoRepository repository;

    public PedidoOutput getPedido(Long id) {
        return repository.findById(id).orElse(null);
    }

}
