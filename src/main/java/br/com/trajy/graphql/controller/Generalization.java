package br.com.trajy.graphql.controller;

import br.com.trajy.graphql.model.output.Cliente;

public class Generalization {

    public Cliente test() {
        Cliente cliente = new Cliente();
        cliente.setNome("Olha");
        return cliente;
    }

    public String documento(Cliente c) {
        return "Documento Testado";
    }
}
