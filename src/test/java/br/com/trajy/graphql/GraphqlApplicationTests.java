package br.com.trajy.graphql;


import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNotNull;

import br.com.trajy.graphql.objectmother.ClienteMother;
import br.com.trajy.graphql.types.Cliente;
import com.netflix.graphql.dgs.DgsQueryExecutor;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.LinkedHashMap;

@SpringBootTest
class GraphqlApplicationTests {

	@Autowired
	private DgsQueryExecutor queryExecutor;

	@Test
	void saveGraphQLTest() {
		String saveGraphQlQuery = "{" +
				"clienteController {" +
				"save(cliente: {" +
				"nome: \"" + ClienteMother.CLIENTE.getNome() + "\"" +
				"documento: \"" + ClienteMother.CLIENTE.getDocumento() +"\"" +
				"pedidos: [{" +
				"codigo: \"1234\"" +
				"}]})}}";
		ClienteMother.id = queryExecutor.executeAndExtractJsonPathAsObject(saveGraphQlQuery, "data.clienteController.save", String.class);
		assertNotNull("id nao retornado ao salvar", ClienteMother.id);
	}

	@Test
	void findByIdGraphQLTest() {
		final String findGraphQlQuery = "{" +
				"clienteController {" +
				"findById(id: \"" + ClienteMother.id +"\") {" +
				"nome\n" +
				"documento\n" +
				"pedidos {" +
				"id\n" +
				"codigo" +
				"}}}}";
		Cliente cliente = queryExecutor.executeAndExtractJsonPathAsObject(findGraphQlQuery, "data.clienteController.findById", Cliente.class);
		assertEquals("O nome nao foi salvo corretamente", ClienteMother.CLIENTE.getNome(), cliente.getNome());

	}

	@Test
	void findGraphQLTest() {
		final String filter = "{nome: \"" + ClienteMother.FILTER.getNome() + "\"}";
		final String query = "{ clienteController { find(filter:" + filter + ") { nome }}}";
		JSONArray clientes = queryExecutor.executeAndExtractJsonPath(query,"data.clienteController.find[*]");
		clientes.forEach(cliente -> assertEquals("find nao retorna elementos corretos", ClienteMother.FILTER.getNome(), LinkedHashMap.class.cast(cliente).get("nome")));
  }

}
