package br.com.trajy.graphql.resolver.domain;

import static br.com.trajy.graphql.util.SubscriptionGraphQlUtil.publish;
import static br.com.trajy.graphql.util.SubscriptionGraphQlUtil.subscribe;
import static java.lang.Long.valueOf;

import br.com.trajy.graphql.assembly.ProblemAssembly;
import br.com.trajy.graphql.codegen.tad.Problem;
import br.com.trajy.graphql.codegen.tad.ProblemInput;
import br.com.trajy.graphql.codegen.tad.ProblemSubscriptionTopic;
import br.com.trajy.graphql.util.SubscriptionGraphQlUtil.SubscriptionModel;
import br.com.trajy.graphql.service.ProblemService;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsSubscription;
import lombok.RequiredArgsConstructor;
import lombok.experimental.ExtensionMethod;
import org.reactivestreams.Publisher;
import org.springframework.web.bind.annotation.RequestHeader;
import java.util.List;

@ExtensionMethod({
        ProblemAssembly.class
})
@DgsComponent
@RequiredArgsConstructor
public class ProblemDomainResolver {

    private final ProblemService service;

    @DgsData(parentType = "ProblemDomainQuery")
    public List<Problem> problemzLatest() {
        return service.findLatest().problemzToGraphQlModel();
    }

    @DgsData(parentType = "ProblemDomainQuery")
    public Problem problemDetail(String id) {
        return service.findById(valueOf(id)).problemToGraphQlModel();
    }

    @DgsData(parentType = "ProblemDomainMutation")
    public Problem createProblem(ProblemInput input, @RequestHeader String authorization) {
        return publish(
                ProblemSubscriptionTopic.ADD,
                service.save(input.problemToEntity(), authorization).problemToGraphQlModel()
        );
    }

    @DgsSubscription
    public Publisher<SubscriptionModel<Problem, ProblemSubscriptionTopic>> problemSubscribe() {
        return subscribe(ProblemSubscriptionTopic.class);
    }

    public List<Problem> findByKeyword(String keyword) {
        return service.findByKeyword(keyword).problemzToGraphQlModel();
    }

}
