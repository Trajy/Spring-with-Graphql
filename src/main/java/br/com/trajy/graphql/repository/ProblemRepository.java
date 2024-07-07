package br.com.trajy.graphql.repository;

import br.com.trajy.graphql.entity.ProblemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProblemRepository extends JpaRepository<ProblemEntity, Long> {

    List<ProblemEntity> findAllByOrderByCreationTimestampDesc();

    @Query(nativeQuery = true,
        value = "SELECT * FROM problemz"
        + " WHERE upper(content) LIKE upper(:keyword)"
        + " OR upper(tags) LIKE upper(:keyword)"
        + " OR upper(title) LIKE upper(:keyword)"
    )
    List<ProblemEntity> findByKeyword(String keyword);

}
