package br.com.trajy.graphql.resolver;

import br.com.trajy.graphql.controller.AnimalController;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import lombok.RequiredArgsConstructor;

@DgsComponent
@RequiredArgsConstructor
public class AnimalResolver {

    private final AnimalController controller;

    @DgsQuery
    public AnimalController animalController() {
        return this.controller;
    }

}
