package br.com.trajy.graphql.model.output;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;

import br.com.trajy.graphql.types.CategoriaProduto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.net.URL;

@Data
@JsonInclude(NON_NULL)
@Entity
public class ProdutoOutput {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String nome;

    private BigDecimal preco;

    private Integer quantidade;

    private URL imageUrl;

    @Enumerated(STRING)
    private CategoriaProduto categoria;

}
