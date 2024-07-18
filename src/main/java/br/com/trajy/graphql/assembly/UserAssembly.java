package br.com.trajy.graphql.assembly;

import static br.com.trajy.graphql.util.TrainWreckUtil.nullIfWreck;

import br.com.trajy.graphql.codegen.tad.User;
import br.com.trajy.graphql.codegen.tad.UserAuthToken;
import br.com.trajy.graphql.codegen.tad.UserResponse;
import br.com.trajy.graphql.model.entity.UserEntity;
import br.com.trajy.graphql.model.entity.UserTokenEntity;
import br.com.trajy.graphql.model.transients.UserTransient;
import br.com.trajy.graphql.util.CommonUtil;
import lombok.experimental.ExtensionMethod;
import lombok.experimental.UtilityClass;

@ExtensionMethod({
        ProblemAssembly.class,
        SolutionAssembly.class,
        CommonUtil.class
})
@UtilityClass
public class UserAssembly {

    public UserResponse userResponseTransientToGraphQlModel(UserTransient transientModel) {
        return nullIfWreck(() -> UserResponse.builder()
                .setUser(userToGraphQlModel(transientModel.getUser()))
                .setAuthToken(userTokenToGraphQlModel(transientModel.getUserToken()))
                .build()
        );
    }

    public User userToGraphQlModel(UserEntity entity) {
        preventCiclicReference(entity);
        return nullIfWreck(() -> User.builder()
                .setId(entity.getId().toStringNullSafe())
                .setUsername(entity.getUsername())
                .setDisplayName(entity.getDisplayName())
                .setEmail(entity.getEmail())
                .setAvatar(entity.getAvatarUrl())
                .setProblemz(entity.getProblemz().problemzToGraphQlModel())
                .setSolutionz(entity.getSolutionz().solutionzToGraphQlModel())
                .setCreatedAt(entity.getCreationTimestamp())
                .build()
        );
    }

    public UserAuthToken userTokenToGraphQlModel(UserTokenEntity entity) {
        return nullIfWreck(() -> UserAuthToken.builder()
                .setToken(entity.getAuthToken())
                .setExpiryTime(entity.getExpiryTimestamp())
                .build()
        );
    }

    private void preventCiclicReference(UserEntity entity) {
        nullIfWreck(() -> entity.getProblemz().forEach(problemEntity -> problemEntity.setAuthor(null)));
    }

}
