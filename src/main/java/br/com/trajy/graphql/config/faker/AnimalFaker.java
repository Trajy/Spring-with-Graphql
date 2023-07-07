package br.com.trajy.graphql.config.faker;

import br.com.trajy.graphql.types.Animal;
import br.com.trajy.graphql.types.Cachorro;
import br.com.trajy.graphql.types.Gato;
import br.com.trajy.graphql.types.TipoAlimentoAnimal;
import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class AnimalFaker {

    public static final List<Animal> ANIMAIS = new ArrayList<>();

    private final Faker faker;

    @PostConstruct
    private void postConstruct() {
        for(int i = 0; i < 10; i++) {
            switch (i % 2) {
                case 0:
                     ANIMAIS.add(
                             Cachorro.newBuilder()
                                    .nome(faker.dog().name())
                                    .tipoComida(TipoAlimentoAnimal.CARNIVORO)
                                    .raca(faker.dog().breed())
                                    .tamanho(faker.dog().size())
                                    .comprimentoPelo(faker.dog().coatLength())
                                    .build()
                     );
                default:
                    ANIMAIS.add(
                            Gato.newBuilder()
                                    .nome(faker.cat().name())
                                    .tipoComida(TipoAlimentoAnimal.ONIVORO)
                                    .raca(faker.cat().breed())
                                    .registro(faker.cat().registry())
                                    .build()
                    );
            }
        }
    }

}
