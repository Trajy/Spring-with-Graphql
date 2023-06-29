package br.com.trajy.graphql.controller;

import br.com.trajy.graphql.model.output.ClienteOutput;

public class Generalization {

    public static String TYPE_NAME;

    public ClienteOutput test() {
        ClienteOutput cliente = new ClienteOutput();
        cliente.setNome("Olha");
        return cliente;
    }
}
