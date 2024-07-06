package br.com.trajy.graphql.resolver.domain;

import static java.lang.Long.valueOf;

import br.com.trajy.graphql.assembly.ProblemAssembly;
import br.com.trajy.graphql.codegen.tad.Problem;
import br.com.trajy.graphql.codegen.tad.ProblemInput;
import br.com.trajy.graphql.codegen.tad.ProblemResponse;
import br.com.trajy.graphql.service.ProblemService;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import lombok.RequiredArgsConstructor;
import lombok.experimental.ExtensionMethod;
import java.util.List;

@ExtensionMethod({
        ProblemAssembly.class
})
@DgsComponent
@RequiredArgsConstructor
public class ProblemDomainResolver {

    private final ProblemService problemService;

    @DgsData(parentType = "ProblemDomainQuery")
    public List<Problem> problemzLatest() {
        return problemService.findLatest().problemzToGraphQlModel();
    }

    @DgsData(parentType = "ProblemDomainQuery")
    public Problem problemDetail(String id) {
        return problemService.findById(valueOf(id)).problemToGraphQlModel();
    }

    @DgsData(parentType = "ProblemDomainMutation")
    public ProblemResponse createProblem(ProblemInput input) {
        //TODO - to implement
        return ProblemResponse.builder().build();
    }

}
