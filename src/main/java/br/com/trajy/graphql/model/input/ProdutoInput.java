package br.com.trajy.graphql.model.input;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import br.com.trajy.graphql.model.output.Produto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import javax.persistence.Entity;

@Data
@JsonInclude(NON_NULL)
@Entity
public class ProdutoInput extends Produto {

}
