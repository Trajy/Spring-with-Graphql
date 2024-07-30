package br.com.trajy.graphql.service;

import static br.com.trajy.graphql.exception.ConditionUtils.checkEntityNotFound;

import br.com.trajy.graphql.model.entity.UserEntity;
import br.com.trajy.graphql.model.transients.UserTransient;
import br.com.trajy.graphql.repository.UserRepository;
import br.com.trajy.graphql.util.HashUtil;
import com.netflix.graphql.dgs.exceptions.DgsEntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.experimental.ExtensionMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@ExtensionMethod({
        HashUtil.class
})
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    private final UserTokenService userTokenService;

    public UserEntity create(UserEntity entity) {
        return repository.save(entity);
    }

    @Transactional
    public Boolean active(Long userId, Boolean isActive) {
        repository.active(userId, isActive);
        UserEntity entity = repository.findById(userId).orElseThrow(
                () -> new DgsEntityNotFoundException("Invalid user id")
        );
        return entity.getActive();
    }

    public UserEntity findMeByToken(String authToken) {
        return repository.findUserByToken(authToken).orElseThrow();
    }

    public UserTransient login(String username, String password) {
        Optional<UserEntity> user = repository.findByUsernameIgnoreCase(username);
        checkEntityNotFound(user.isEmpty() || !password.isBCriptyMatch(user.get().getHashedPassword()), "Invalid Credentials");
        return UserTransient.builder()
                .user(user.get())
                .userToken(userTokenService.refreshToken(user.get().getId()))
                .build();
    }

}
