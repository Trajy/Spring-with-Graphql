package br.com.trajy.graphql.objectmother;

import static com.github.javafaker.Faker.instance;

import br.com.trajy.graphql.types.Cliente;
import br.com.trajy.graphql.types.ClienteFilter;
import com.github.javafaker.Faker;

public final class ClienteMother {

    private ClienteMother() { }

    public static String id;
    public static final Cliente CLIENTE;
    public static final ClienteFilter FILTER;

    static {
        Faker faker = instance();
        CLIENTE = Cliente.newBuilder()
                .nome(faker.name().name())
                .documento(faker.number().digits(8))
                .build();
        FILTER = ClienteFilter.newBuilder().nome(CLIENTE.getNome()).build();
    }



}
