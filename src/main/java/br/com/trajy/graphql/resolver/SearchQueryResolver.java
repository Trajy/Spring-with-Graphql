package br.com.trajy.graphql.resolver;

import static java.util.Comparator.comparing;

import br.com.trajy.graphql.assembly.ProblemAssembly;
import br.com.trajy.graphql.assembly.SolutionAssembly;
import br.com.trajy.graphql.codegen.tad.SearchInput;
import br.com.trajy.graphql.codegen.tad.Searchable;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import lombok.RequiredArgsConstructor;
import lombok.experimental.ExtensionMethod;
import java.util.ArrayList;
import java.util.List;

@ExtensionMethod({
        ProblemAssembly.class,
        SolutionAssembly.class
})
@DgsComponent
@RequiredArgsConstructor
public class SearchQueryResolver {

    private final Resolver resolver;

    @DgsQuery
    public List<Searchable> search(SearchInput input) {
        List<Searchable> results = new ArrayList<>();
        results.addAll(resolver.getProblemDomainResolver().findByKeyword(input.getKeyword()));
        results.addAll(resolver.getSolutionDomainResolver().findByKeyword(input.getKeyword()));
        results.sort(comparing(Searchable::getCreatedAt));
        return results;
    }

}
