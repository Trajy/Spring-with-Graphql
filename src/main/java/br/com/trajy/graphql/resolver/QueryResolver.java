package br.com.trajy.graphql.resolver;

import br.com.trajy.graphql.resolver.domain.ProblemDomainResolver;
import br.com.trajy.graphql.resolver.domain.SolutionDomainResolver;
import br.com.trajy.graphql.resolver.domain.UserDomainResolver;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@DgsComponent
@RequiredArgsConstructor
public class QueryResolver {

    @Getter(onMethod_ = { @DgsQuery(field = "userDomain"), @DgsMutation(field = "userDomain") })
    private final UserDomainResolver userDomainResolver;

    @Getter(onMethod_ = { @DgsQuery(field = "problemDomain"), @DgsMutation(field = "problemDomain") })
    private final ProblemDomainResolver problemDomainResolver;

    @Getter(onMethod_ = @DgsQuery(field = "solutionDomain"))
    private final SolutionDomainResolver solutionDomainResolver;

}
