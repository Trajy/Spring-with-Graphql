package br.com.trajy.graphql.service;

import static java.time.OffsetDateTime.now;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

import br.com.trajy.graphql.model.entity.UserTokenEntity;
import br.com.trajy.graphql.repository.UserTokenRepository;
import org.springframework.stereotype.Service;

@Service
public class UserTokenService {

    private UserTokenRepository repository;

    UserTokenEntity refreshToken(Long userId) {
        UserTokenEntity entity = repository.findByUserId(userId)
                .orElse(UserTokenEntity.builder().userId(userId).build());
        entity.setAuthToken(randomAlphabetic(40));
        entity.setCreationTimestamp(now());
        entity.setExpiryTimestamp(now().plusHours(2));
        return repository.save(entity);
    }

}
