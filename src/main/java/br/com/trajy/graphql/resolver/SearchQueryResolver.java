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

    private final QueryResolver queryResolver;

    @DgsQuery(field = "search")
    public List<Searchable> search(SearchInput filter) {
        List<Searchable> results = new ArrayList<>();
        results.addAll(queryResolver.getProblemDomainResolver().findByKeyword(filter.getKeyword()));
        results.addAll(queryResolver.getSolutionDomainResolver().findByKeyword(filter.getKeyword()));
        results.sort(comparing(Searchable::getCreatedAt));
        return results;
    }

}
