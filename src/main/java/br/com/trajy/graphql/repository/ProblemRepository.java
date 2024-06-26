package br.com.trajy.graphql.repository;

import br.com.trajy.graphql.entity.ProblemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProblemRepository extends JpaRepository<ProblemEntity, Long> {

    List<ProblemEntity> findAllByOrderByCreationTimestampDesc();

}
