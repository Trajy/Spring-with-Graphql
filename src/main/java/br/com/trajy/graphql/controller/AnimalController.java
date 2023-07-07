package br.com.trajy.graphql.controller;

import static br.com.trajy.graphql.config.faker.AnimalFaker.ANIMAIS;
import static java.util.Objects.isNull;
import static java.util.stream.Collectors.toList;

import br.com.trajy.graphql.types.Animal;
import br.com.trajy.graphql.types.AnimalFilter;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;
import java.util.List;

@DgsComponent
public class AnimalController {

    @DgsData(parentType = "AnimalController")
    public List<Animal> find(@InputArgument AnimalFilter filter) {
        if(isNull(filter)) {
            return ANIMAIS;
        }
        return ANIMAIS.stream()
                .filter(animal -> animal.getClass().getSimpleName().equals(filter.getTipoAnimal()))
                .collect(toList());
    }

}
