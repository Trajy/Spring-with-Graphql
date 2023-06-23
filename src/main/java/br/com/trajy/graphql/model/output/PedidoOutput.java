package br.com.trajy.graphql.model.output;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.IDENTITY;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.List;

@Data
@JsonInclude(NON_NULL)
@Entity
public class PedidoOutput {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String codigo;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private ClienteOutput cliente;

    @ManyToMany(cascade = ALL)
    @JoinTable(
            name = "pedidos_produtos",
            joinColumns = @JoinColumn(
                    name = "produto_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "pedido_id",
                    referencedColumnName = "id"
            )
    )
    private List<ProdutoOutput> produtos;

}
