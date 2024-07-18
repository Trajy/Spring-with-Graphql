package br.com.trajy.graphql.repository;

import br.com.trajy.graphql.model.entity.SolutionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SolutionRepository extends JpaRepository<SolutionEntity, Long> {

    @Modifying
    @Query(
            nativeQuery = true,
            value = "UPDATE solutionz SET vote_as_bad = vote_as_bad + 1 WHERE id = :id"
    )
    void incrementBadVote(Long id);

    @Modifying
    @Query(
            nativeQuery = true,
            value = "UPDATE solutionz SET vote_as_good = vote_as_good + 1 WHERE id = :id"
    )
    void incrementGoodVote(Long id);

    @Query(
            nativeQuery = true,
            value = "SELECT * FROM solutionz WHERE UPPER(content) LIKE UPPER(:keyword)"
    )
    List<SolutionEntity> findByKeyword(String keyword);

}
