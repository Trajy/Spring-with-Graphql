package br.com.trajy.graphql.model.transients;

import br.com.trajy.graphql.model.entity.UserEntity;
import br.com.trajy.graphql.model.entity.UserTokenEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Data
@SuperBuilder
public class UserTransient {

    private UserEntity user;

    private UserTokenEntity userToken;

}
