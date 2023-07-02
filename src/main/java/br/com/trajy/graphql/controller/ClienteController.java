package br.com.trajy.graphql.controller;

import static br.com.trajy.graphql.util.TrainWreckUtil.nullIfWreck;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.lang.Long.valueOf;
import static java.util.Objects.nonNull;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

import br.com.trajy.graphql.DgsConstants;
import br.com.trajy.graphql.model.input.ClienteInput;
import br.com.trajy.graphql.repository.ClienteRepository;
import br.com.trajy.graphql.types.Cliente;
import br.com.trajy.graphql.types.ClienteFilter;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;
import graphql.schema.DataFetchingEnvironment;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;

@DgsComponent
@RequiredArgsConstructor
public class ClienteController {

    private final ModelMapper modelMapper;
    private final ClienteRepository repository;

    @DgsData(parentType = "ClienteController")
    @Transactional(readOnly = true)
    public List<Cliente> find(DataFetchingEnvironment dataFetchingEnvironment) {
        Map<String, ?> clienteFilterAttributes = dataFetchingEnvironment.getArgument("filter");
        ClienteFilter filter = ClienteFilter.newBuilder()
                .nome((String) clienteFilterAttributes.get(DgsConstants.CLIENTE_FILTER.Nome))
                .documento((String) clienteFilterAttributes.get(DgsConstants.CLIENTE_FILTER.Documento))
                .build();
        return this.repository.findAll().stream()
                    .filter(entity -> ofNullable(nullIfWreck(() -> filter.getNome().equals(entity.getNome()))).orElse(true))
                    .filter(entity -> ofNullable(nullIfWreck(() -> filter.getDocumento().equals(entity.getDocumento()))).orElse(true))
                    .map(entity -> this.modelMapper.map(entity, Cliente.class)).collect(toList());
    }

    @DgsData(parentType = "ClienteController")
    @Transactional(readOnly = true)
    public Cliente findById(@InputArgument String id) {

        return modelMapper.map(this.repository.findById(valueOf(id)).orElse(null), Cliente.class);
    }

    @DgsData(parentType = "ClienteController")
    @Transactional
    public Long save(@InputArgument Cliente cliente) {
        return this.repository.save(this.modelMapper.map(cliente, ClienteInput.class)).getId();
    }

    @DgsData(parentType = "ClienteController")
    public Cliente update(@InputArgument Cliente cliente) {
        return modelMapper.map(this.repository.save(modelMapper.map(cliente, ClienteInput.class)), Cliente.class);
    }

    @DgsData(parentType = "ClienteController")
    public Boolean delete(@InputArgument String id) {
        Cliente cliente = this.findById(id);
        if(nonNull(cliente)) {
            this.repository.delete(this.modelMapper.map(cliente, ClienteInput.class));
            return TRUE;
        }
        return FALSE;
    }

}
