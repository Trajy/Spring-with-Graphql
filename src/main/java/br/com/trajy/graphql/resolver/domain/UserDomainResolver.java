package br.com.trajy.graphql.resolver.domain;

import br.com.trajy.graphql.codegen.tad.User;
import br.com.trajy.graphql.codegen.tad.UserActivationInput;
import br.com.trajy.graphql.codegen.tad.UserActivationResponse;
import br.com.trajy.graphql.codegen.tad.UserCreateInput;
import br.com.trajy.graphql.codegen.tad.UserLoginInput;
import br.com.trajy.graphql.codegen.tad.UserResponse;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import lombok.RequiredArgsConstructor;

@DgsComponent
@RequiredArgsConstructor
public class UserDomainResolver {

    @DgsData(parentType = "UserDomainQuery")
    public User me() {
        //TODO - to implement
        return User.builder().build();
    }

    @DgsData(parentType = "UserDomainMutation")
    public UserResponse createUser(UserCreateInput input) {
        //TODO - to implement
        return UserResponse.builder().build();
    }

    @DgsData(parentType = "UserDomainMutation")
    public UserResponse userLogin(UserLoginInput input) {
        //TODO - to implement
        return UserResponse.builder().build();
    }

    @DgsData(parentType = "UserDomainMutation")
    public UserActivationResponse userActivation(UserActivationInput input) {
        //TODO - to implement
        return UserActivationResponse.builder().build();
    }

}
