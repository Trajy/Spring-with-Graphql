package br.com.trajy.graphql.assembly;

import static java.util.Objects.isNull;

import br.com.trajy.graphql.codegen.tad.User;
import br.com.trajy.graphql.codegen.tad.UserAuthToken;
import br.com.trajy.graphql.codegen.tad.UserResponse;
import br.com.trajy.graphql.model.entity.UserEntity;
import br.com.trajy.graphql.model.entity.UserTokenEntity;
import br.com.trajy.graphql.model.transients.UserResponseTransient;
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

    public UserResponse userResponseTransientToGraphQlModel(UserResponseTransient transientModel) {
        return UserResponse.builder()
                .setUser(userToGraphQlModel(transientModel.getUser()))
                .setAuthToken(userTokenToGraphQlModel(transientModel.getUserToken()))
                .build();
    }

    public User userToGraphQlModel(UserEntity entity) {
        if(isNull(entity)) {
            return null;
        }
        User user = User.builder()
                .setId(entity.getId().toStringNullSafe())
                .setUsername(entity.getUsername())
                .setDisplayName(entity.getDisplayName())
                .setEmail(entity.getEmail())
                .setAvatar(entity.getAvatarUrl())
                .setSolutionz(entity.getSolutionz().solutionzToGraphQlModel())
                .setCreatedAt(entity.getCreationTimestamp())
                .build();
                if(entity.getProblemz() != null) {
                    entity.getProblemz().forEach(problemEntity -> {
                        if (problemEntity.getAuthor() != null) {
                            problemEntity.setAuthor(null);
                        }
                    });
                    user.setProblemz(entity.getProblemz().problemzToGraphQlModel());
                }
        return user;
    }

    public UserAuthToken userTokenToGraphQlModel(UserTokenEntity entity) {
        return UserAuthToken.builder()
                .setToken(entity.getAuthToken())
                .setExpiryTime(entity.getExpiryTimestamp())
                .build();
    }

}
