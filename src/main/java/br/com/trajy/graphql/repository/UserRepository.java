package br.com.trajy.graphql.repository;

import br.com.trajy.graphql.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsernameIgnoreCase(String username);

    @Query(nativeQuery = true,
        value = "SELECT u.* FROM userz "
            + "u INNER JOIN userz_token ut "
            + "ON u.id = ut.user_id "
            + "WHERE ut.auth_token = ? "
            + "AND ut.expiry_timestamp > CURRENT_TIMESTAMP"
    )
    Optional<UserEntity> findUserByToken(String authToken);

    @Modifying
    @Query(nativeQuery = true,
            value = "UPDATE userz SET active = :isActive WHERE UPPER(id) = UPPER(:userId)"
    )
    void active(Long userId, Boolean isActive);

}
