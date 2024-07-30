package br.com.trajy.graphql.resolver.domain;

import static br.com.trajy.graphql.codegen.tad.SolutionMessageTopic.ADD;
import static br.com.trajy.graphql.codegen.tad.SolutionMessageTopic.VOTE;
import static java.lang.Long.valueOf;

import br.com.trajy.graphql.assembly.SolutionAssembly;
import br.com.trajy.graphql.codegen.tad.Solution;
import br.com.trajy.graphql.codegen.tad.SolutionInput;
import br.com.trajy.graphql.codegen.tad.SolutionVoteInput;
import br.com.trajy.graphql.service.SolutionService;
import br.com.trajy.graphql.util.DgsSubscriptionUtil;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import lombok.RequiredArgsConstructor;
import lombok.experimental.ExtensionMethod;
import org.springframework.web.bind.annotation.RequestHeader;
import java.util.List;

@ExtensionMethod({
        SolutionAssembly.class,
        DgsSubscriptionUtil.class
})
@DgsComponent
@RequiredArgsConstructor
public class SolutionDomainResolver {

    private final SolutionService service;

    @DgsData(parentType =  "SolutionDomainQuery")
    public Solution solutionDetail(String id) {
        return service.findById(valueOf(id)).solutionToGraphQlModel();
    }

    @DgsData(parentType = "SolutionDomainMutation")
    public Solution createSolution(SolutionInput input, @RequestHeader String authorization) {
        return service.save(input.solutionToEntity(), authorization).solutionToGraphQlModel()
                .publish(ADD);
    }

    @DgsData(parentType = "SolutionDomainMutation")
    public Solution voteSolution(SolutionVoteInput input) {
        service.vote(valueOf(input.getSolutionId()), input.getVoteAsGood());
        return this.solutionDetail(input.getSolutionId()).publish(VOTE);
    }

    public List<Solution> findByKeyword(String keyword) {
        return service.findByKeyword(keyword).solutionzToGraphQlModel();
    }

}
