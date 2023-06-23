package br.com.trajy.graphql.controller;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.lang.Long.valueOf;
import static java.util.Objects.nonNull;

import br.com.trajy.graphql.model.input.ClienteInput;
import br.com.trajy.graphql.repository.ClienteRepository;
import br.com.trajy.graphql.types.Cliente;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;

@DgsComponent
@Controller
@RequiredArgsConstructor
public class ClienteController {

    private final ModelMapper modelMapper;
    private final ClienteRepository repository;

    @DgsData(parentType = "ClienteController")
    public Cliente findById(@InputArgument String id) {
        return modelMapper.map(this.repository.findById(valueOf(id)).orElse(null), Cliente.class);
    }

    @DgsData(parentType = "ClienteController")
    public Long save(@InputArgument Cliente cliente) {

        return this.repository.save(modelMapper.map(cliente, ClienteInput.class)).getId();
    }

    @DgsData(parentType = "ClienteController")
    public Cliente update(@InputArgument Cliente cliente) {
        return modelMapper.map(this.repository.save(modelMapper.map(cliente, ClienteInput.class)), Cliente.class);
    }

    @DgsData(parentType = "ClienteController")
    public Boolean delete(@InputArgument String id) {
        Cliente cliente = this.findById(id);
        if(nonNull(cliente)) {
            this.repository.delete(modelMapper.map(cliente, ClienteInput.class));
            return TRUE;
        }
        return FALSE;
    }

}
