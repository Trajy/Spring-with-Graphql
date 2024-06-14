package br.com.trajy.graphql.assembly;

import br.com.trajy.graphql.codegen.tad.User;
import br.com.trajy.graphql.codegen.tad.UserAuthToken;
import br.com.trajy.graphql.entity.UserEntity;
import br.com.trajy.graphql.entity.UserTokenEntity;
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

    public User userToGraphQlModel(UserEntity entity) {
        User user = User.builder()
                .setId(entity.getId().toString())
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
