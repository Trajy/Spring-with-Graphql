package br.com.trajy.graphql.resolver.domain;

import br.com.trajy.graphql.assembly.SolutionAssembly;
import br.com.trajy.graphql.codegen.tad.Solution;
import br.com.trajy.graphql.codegen.tad.SolutionInput;
import br.com.trajy.graphql.codegen.tad.SolutionResponse;
import br.com.trajy.graphql.codegen.tad.SolutionVoteInput;
import br.com.trajy.graphql.service.SolutionService;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import lombok.RequiredArgsConstructor;
import lombok.experimental.ExtensionMethod;
import java.util.List;

@ExtensionMethod({
        SolutionAssembly.class
})
@DgsComponent
@RequiredArgsConstructor
public class SolutionDomainResolver {

    private final SolutionService service;

    @DgsData(parentType =  "SolutionDomainQuery")
    public Solution solutionDetail(String id) {
        //TODO - to implement
        return Solution.builder().build();
    }

    @DgsData(parentType = "SolutionDomainMutation")
    public SolutionResponse createSolution(SolutionInput input) {
        //TODO - to implement
        return SolutionResponse.builder().build();
    }

    @DgsData(parentType = "SolutionDomainMutation")
    public SolutionResponse voteSolution(SolutionVoteInput input) {
        //TODO - to implement
        return SolutionResponse.builder().build();
    }

    public List<Solution> findByKeyword(String keyword) {
        return service.findByKeyword(keyword).solutionzToGraphQlModel();
    }

}
