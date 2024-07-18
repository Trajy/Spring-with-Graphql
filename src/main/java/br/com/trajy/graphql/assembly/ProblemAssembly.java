package br.com.trajy.graphql.assembly;

import static br.com.trajy.graphql.util.TrainWreckUtil.nullIfWreck;
import static java.lang.String.join;
import static java.util.List.of;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.collections4.ListUtils.emptyIfNull;
import static org.apache.commons.lang3.StringUtils.SPACE;

import br.com.trajy.graphql.codegen.tad.Problem;
import br.com.trajy.graphql.codegen.tad.ProblemInput;
import br.com.trajy.graphql.model.entity.ProblemEntity;
import br.com.trajy.graphql.util.CommonUtil;
import lombok.experimental.ExtensionMethod;
import lombok.experimental.UtilityClass;
import java.util.List;

@ExtensionMethod({
        UserAssembly.class,
        SolutionAssembly.class,
        CommonUtil.class
})
@UtilityClass
public class ProblemAssembly {

    public ProblemEntity problemToEntity(ProblemInput input) {
        return nullIfWreck(() -> ProblemEntity.builder()
                .title(input.getTitle())
                .content(input.getContent())
                .tags(join(SPACE, input.getTags()))
                .build()
        );
    }

    public Problem problemToGraphQlModel(ProblemEntity entity) {
        preventCiclicReference(entity);
        return nullIfWreck(() -> Problem.builder()
                .setId(entity.getId().toString())
                .setTitle(entity.getTitle())
                .setAuthor(entity.getAuthor().userToGraphQlModel())
                .setContent(entity.getContent())
                .setTags(of(entity.getTags().split(SPACE)))
                .setSolutionCount(emptyIfNull(entity.getSolutionz()).size())
                .setSolutionz(entity.getSolutionz().solutionzToGraphQlModel())
                .setCreatedAt(entity.getCreationTimestamp())
                .build()
        );
    }

    public List<Problem> problemzToGraphQlModel(List<ProblemEntity> entities) {
        return nullIfWreck(() -> entities.stream().map(ProblemAssembly::problemToGraphQlModel).collect(toList()));
    }

    private void preventCiclicReference(ProblemEntity entity) {
        nullIfWreck(() -> entity.getAuthor().setProblemz(null));
        nullIfWreck(() -> entity.getSolutionz().forEach(solutionEntity -> solutionEntity.setProblem(null)));
    }

}
