package br.com.trajy.graphql.model.input;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.IDENTITY;

import br.com.trajy.graphql.model.output.Cliente;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@JsonInclude(NON_NULL)
@Entity
public class ClienteInput extends Cliente {

}
