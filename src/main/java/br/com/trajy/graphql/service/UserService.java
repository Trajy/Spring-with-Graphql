package br.com.trajy.graphql.service;

import static java.time.OffsetDateTime.now;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

import br.com.trajy.graphql.model.entity.UserEntity;
import br.com.trajy.graphql.model.entity.UserTokenEntity;
import br.com.trajy.graphql.model.transients.UserTransient;
import br.com.trajy.graphql.repository.UserRepository;
import br.com.trajy.graphql.repository.UserTokenRepository;
import br.com.trajy.graphql.util.HashUtil;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.ExtensionMethod;
import org.springframework.stereotype.Service;
import java.util.Optional;

@ExtensionMethod({
        HashUtil.class
})
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    private final UserTokenRepository tokenRepository;

    public UserEntity findMeByToken(String authToken) {
        return repository.findUserByToken(authToken).orElseThrow();
    }

    @SneakyThrows(value = IllegalAccessException.class)
    public UserTransient login(String username, String password) {
        Optional<UserEntity> user = repository.findByUsernameIgnoreCase(username);
        if(user.isEmpty() || !password.isBCriptyMatch(user.get().getHashedPassword())) {
            throw new IllegalAccessException("Invalid Credentials");
        }
        return UserTransient.builder()
                .user(user.get())
                .userToken(refreshToken(user.get().getId()))
                .build();
    }

    private UserTokenEntity refreshToken(Long userId) {
        UserTokenEntity entity = tokenRepository.findByUserId(userId)
                .orElse(UserTokenEntity.builder().userId(userId).build());
        entity.setAuthToken(randomAlphabetic(40));
        entity.setCreationTimestamp(now());
        entity.setExpiryTimestamp(now().plusHours(2));
        return tokenRepository.save(entity);
    }

}
