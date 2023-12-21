package br.com.trajy.graphql.assembly;

import br.com.trajy.graphql.codegen.tad.User;
import br.com.trajy.graphql.entity.UserEntity;
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
        return User.builder()
                .setId(entity.getId().toString())
                .setUsername(entity.getUsername())
                .setDisplayName(entity.getDisplayName())
                .setEmail(entity.getEmail())
                .setAvatar(entity.getAvatarUrl().toStringNullSafe())
                .setProblemz(entity.getProblemz().problemzToGraphQlModel())
                .setSolutionz(entity.getSolutionz().solutionzToGraphQlModel())
                .setCreatedAt(entity.getCreationTimestamp().toStringNullSafe())
                .build();
    }

}
