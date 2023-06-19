package br.com.trajy.graphql.controller;


import br.com.trajy.graphql.model.Produto;
import br.com.trajy.graphql.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoRepository repository;

    public Produto getProduto(@Argument Long id) {
        return repository.findById(id).orElse(null);
    }

}
