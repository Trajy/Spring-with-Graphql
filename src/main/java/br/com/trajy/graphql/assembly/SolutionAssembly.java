package br.com.trajy.graphql.assembly;

import static br.com.trajy.graphql.util.TrainWreckUtil.nullIfWreck;
import static java.lang.Long.valueOf;
import static java.util.stream.Collectors.toList;

import br.com.trajy.graphql.codegen.tad.Solution;
import br.com.trajy.graphql.codegen.tad.SolutionInput;
import br.com.trajy.graphql.model.entity.ProblemEntity;
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

    public SolutionEntity solutionToEntity(SolutionInput input) {
        return nullIfWreck(() -> SolutionEntity.builder()
                .content(input.getContent())
                .problem(ProblemEntity.builder().id(valueOf(input.getProblemId())).build())
                .category(input.getCategory())
                .build()
        );
    }

    public Solution solutionToGraphQlModel(SolutionEntity entity) {
        preventCiclicReference(entity);
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

    private void preventCiclicReference(SolutionEntity entity) {
        nullIfWreck(() -> entity.getProblem().setSolutionz(null));
        nullIfWreck(() -> entity.getAuthor().setSolutionz(null));
    }

}
