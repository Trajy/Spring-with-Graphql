package br.com.trajy.graphql.model.output;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static javax.persistence.GenerationType.IDENTITY;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.math.BigDecimal;
import java.net.URL;
import java.util.List;

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

}
