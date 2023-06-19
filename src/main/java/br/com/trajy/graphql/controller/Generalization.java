package br.com.trajy.graphql.controller;

import br.com.trajy.graphql.model.Cliente;
import org.springframework.graphql.data.method.annotation.SchemaMapping;

public class Generalization {

    @SchemaMapping
    public Cliente test() {
        Cliente cliente = new Cliente();
        cliente.setNome("Olha");
        return cliente;
    }

    @SchemaMapping(typeName = "Cliente")
    public String documento(Cliente c) {
        return "Documento Testado";
    }
}
