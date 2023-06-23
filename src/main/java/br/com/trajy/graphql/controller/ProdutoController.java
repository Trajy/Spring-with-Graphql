package br.com.trajy.graphql.controller;


import br.com.trajy.graphql.model.output.ProdutoOutput;
import br.com.trajy.graphql.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoRepository repository;

    public ProdutoOutput getProduto(Long id) {
        return repository.findById(id).orElse(null);
    }

}
