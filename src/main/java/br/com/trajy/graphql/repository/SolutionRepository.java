package br.com.trajy.graphql.repository;

import br.com.trajy.graphql.entity.SolutionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolutionRepository extends JpaRepository<SolutionEntity, Long> {

}
