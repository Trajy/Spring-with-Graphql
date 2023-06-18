package br.com.trajy.graphql.model;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static javax.persistence.GenerationType.IDENTITY;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.math.BigDecimal;
import java.util.List;

@Data
@JsonInclude(NON_NULL)
@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String nome;

    private BigDecimal preco;

    @ManyToMany(mappedBy = "produtos")
    private List<Pedido> pedidos;

}
