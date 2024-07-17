package br.com.trajy.graphql.assembly;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.toList;
import static org.springframework.beans.factory.support.ManagedList.of;

import br.com.trajy.graphql.codegen.tad.Problem;
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

    public Problem problemToGraphQlModel(ProblemEntity entity) {
        if(isNull(entity)) {
            return null;
        }
        preventCiclicReference(entity);
        return Problem.builder()
                .setId(entity.getId().toString())
                .setTitle(entity.getTitle())
                .setAuthor(entity.getAuthor().userToGraphQlModel())
                .setContent(entity.getContent())
                .setTags(of(entity.getTags().split(", ")))
                .setSolutionCount(entity.getSolutionz().size())
                .setSolutionz(entity.getSolutionz().solutionzToGraphQlModel())
                .setCreatedAt(entity.getCreationTimestamp())
                .build();
    }

    public List<Problem> problemzToGraphQlModel(List<ProblemEntity> entities) {
        return entities.stream().map(ProblemAssembly::problemToGraphQlModel).collect(toList());
    }

    private void preventCiclicReference(ProblemEntity entity) {
        if(nonNull(entity.getAuthor())) {
            entity.getAuthor().setProblemz(null);
        }
        entity.getSolutionz().forEach(solutionEntity -> solutionEntity.setProblem(null));
    }

}
