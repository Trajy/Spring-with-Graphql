package br.com.trajy.graphql.repository;

import br.com.trajy.graphql.entity.SolutionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SolutionRepository extends JpaRepository<SolutionEntity, Long> {

    @Query(nativeQuery = true,
        value = "SELECT * FROM solutionz WHERE UPPER(content) LIKE UPPER(:keyword)"
    )
    List<SolutionEntity> findByKeyword(String keyword);

}
