package br.com.trajy.graphql;

import static com.github.javafaker.Faker.instance;
import static org.springframework.test.util.AssertionErrors.assertEquals;

import br.com.trajy.graphql.model.Cliente;
import com.netflix.graphql.dgs.DgsQueryExecutor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GraphqlApplicationTests {

	@Autowired
	private DgsQueryExecutor queryExecutor;

	@Test
	void contextLoads() {
	}

	@Test
	public void graphQlTest() {
		String clienteNome = instance().name().name();
		String saveGraphQlQuery = "{" +
				"clienteController {" +
				"save(cliente: {" +
				"nome: \"" + clienteNome + "\"" +
				"documento: \"1234444\"" +
				"pedidos: [{" +
				"codigo: \"1234\"" +
				"}]})}}";
		String id = queryExecutor.executeAndExtractJsonPathAsObject(saveGraphQlQuery, "data.clienteController.save", String.class);
		String findGraphQlQuery = "{" +
				"clienteController {" +
				"findById(id: \"" + id +"\") {" +
				"nome\n" +
				"documento\n" +
				"pedidos {" +
				"id\n" +
				"codigo" +
				"}}}}";
		Cliente cliente = queryExecutor.executeAndExtractJsonPathAsObject(findGraphQlQuery, "data.clienteController.findById", Cliente.class);
		assertEquals("O nome nao foi salvo corretamente", clienteNome, cliente.getNome());
	}
}


