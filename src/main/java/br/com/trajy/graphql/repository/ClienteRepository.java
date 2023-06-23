package br.com.trajy.graphql.repository;

import br.com.trajy.graphql.model.output.ClienteOutput;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteOutput, Long> {

}
