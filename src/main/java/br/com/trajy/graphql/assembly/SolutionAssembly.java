package br.com.trajy.graphql.assembly;

import static br.com.trajy.graphql.util.TrainWreckUtil.nullIfWreck;
import static java.util.stream.Collectors.toList;

import br.com.trajy.graphql.codegen.tad.Solution;
import br.com.trajy.graphql.model.entity.SolutionEntity;
import br.com.trajy.graphql.util.CommonUtil;
import lombok.experimental.ExtensionMethod;
import lombok.experimental.UtilityClass;
import java.util.List;

@ExtensionMethod({
        UserAssembly.class,
        ProblemAssembly.class,
        CommonUtil.class
})
@UtilityClass
public class SolutionAssembly {

    public Solution solutionToGraphQlModel(SolutionEntity entity) {
        return nullIfWreck(() -> Solution.builder()
                .setId(entity.getId().toString())
                .setContent(entity.getContent())
                .setCategory(entity.getCategory())
                .setVoteAsGood(entity.getVoteAsGood())
                .setVoteAsBad(entity.getVoteAsBad())
                .setAuthor(entity.getAuthor().userToGraphQlModel())
                .setProblem(entity.getProblem().problemToGraphQlModel())
                .setCreatedAt(entity.getCreationTimestamp())
                .build()
        );
    }

    public List<Solution> solutionzToGraphQlModel(List<SolutionEntity> entities) {
        return nullIfWreck(() -> (entities).stream().map(SolutionAssembly::solutionToGraphQlModel).collect(toList()));
    }

}
