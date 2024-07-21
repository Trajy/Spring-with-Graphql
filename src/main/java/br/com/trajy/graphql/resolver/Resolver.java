package br.com.trajy.graphql.resolver;

import br.com.trajy.graphql.codegen.tad.Message;
import br.com.trajy.graphql.resolver.domain.ProblemDomainResolver;
import br.com.trajy.graphql.resolver.domain.SolutionDomainResolver;
import br.com.trajy.graphql.resolver.domain.UserDomainResolver;
import br.com.trajy.graphql.util.DgsSubscriptionUtil;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.DgsSubscription;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.reactivestreams.Publisher;

@DgsComponent
@RequiredArgsConstructor
public class Resolver {

    @Getter(onMethod_ = { @DgsQuery(field = "userDomain"), @DgsMutation(field = "userDomain") })
    private final UserDomainResolver userDomainResolver;

    @Getter(onMethod_ = { @DgsQuery(field = "problemDomain"), @DgsMutation(field = "problemDomain") })
    private final ProblemDomainResolver problemDomainResolver;

    @Getter(onMethod_ = { @DgsQuery(field = "solutionDomain"), @DgsMutation(field = "solutionDomain") })
    private final SolutionDomainResolver solutionDomainResolver;

    @DgsSubscription
    public Publisher<Message> subscribe() {
        return DgsSubscriptionUtil.subscribe();
    }

}
