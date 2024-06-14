package br.com.trajy.graphql.resolver;

import static java.lang.Long.valueOf;

import br.com.trajy.graphql.assembly.ProblemAssembly;
import br.com.trajy.graphql.codegen.resolver.QueryResolver;
import br.com.trajy.graphql.codegen.tad.Problem;
import br.com.trajy.graphql.codegen.tad.SearchInput;
import br.com.trajy.graphql.codegen.tad.Searchable;
import br.com.trajy.graphql.codegen.tad.User;
import br.com.trajy.graphql.service.ProblemService;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import lombok.RequiredArgsConstructor;
import lombok.experimental.ExtensionMethod;
import java.util.List;

@ExtensionMethod({
        ProblemAssembly.class
})
@DgsComponent
@RequiredArgsConstructor
public class QueryResolverImpl implements QueryResolver {


    private final ProblemService problemService;

    @Override
    public User me() {
        return null;
    }

    @Override
    @DgsQuery
    public List<Problem> problemzLatest() {
        return problemService.findLatest().problemzToGraphQlModel();
    }

    @Override
    public List<Searchable> search(SearchInput filter) {
        return null;
    }

    @Override
    @DgsQuery
    public Problem problemDetail(String id) {
        return problemService.findById(valueOf(id)).problemToGraphQlModel();
    }
}
