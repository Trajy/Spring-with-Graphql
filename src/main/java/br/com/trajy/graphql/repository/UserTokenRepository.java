package br.com.trajy.graphql.repository;

import br.com.trajy.graphql.model.entity.UserTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserTokenRepository extends JpaRepository<UserTokenEntity, Long> {

    Optional<UserTokenEntity> findByUserId(Long userId);
    
}
