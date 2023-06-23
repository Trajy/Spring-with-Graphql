package br.com.trajy.graphql.repository;

import br.com.trajy.graphql.model.output.ProdutoOutput;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoOutput, Long> {

}
