package br.com.trajy.graphql.controller;

import static br.com.trajy.graphql.config.faker.AnimalFaker.ANIMAIS;
import static java.util.Objects.isNull;
import static java.util.stream.Collectors.toList;

import br.com.trajy.graphql.types.Animal;
import br.com.trajy.graphql.types.AnimalFilter;
import br.com.trajy.graphql.types.Cachorro;
import br.com.trajy.graphql.types.ChachorroAndGato;
import br.com.trajy.graphql.types.Gato;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;
import java.util.ArrayList;
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

    @DgsData(parentType = "AnimalController")
    public List<ChachorroAndGato> findUnion(@InputArgument AnimalFilter filter) {
        List<ChachorroAndGato> unionList = new ArrayList<>();
        List<Cachorro> cachorros = ANIMAIS.stream()
                .filter(animal -> animal.getClass().equals(Cachorro.class))
                .map(Cachorro.class::cast).collect(toList());
        List<Gato> gatos = ANIMAIS.stream()
                .filter(animal -> animal.getClass().equals(Gato.class))
                .map(Gato.class::cast).collect(toList());
        unionList.addAll(cachorros);
        unionList.addAll(gatos);
        if(isNull(filter)) {
            return unionList;
        }
        return unionList.stream()
                .filter(unionTypeElement -> unionTypeElement.getClass().getSimpleName().equals(filter.getTipoAnimal()))
                .collect(toList());
    }

}
