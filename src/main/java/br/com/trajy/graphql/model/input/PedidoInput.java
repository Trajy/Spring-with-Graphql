package br.com.trajy.graphql.model.input;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import br.com.trajy.graphql.model.output.Pedido;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import javax.persistence.Entity;

@Data
@JsonInclude(NON_NULL)
@Entity
public class PedidoInput extends Pedido {

}