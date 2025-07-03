package ar.com.laboratorio.steady.pollo_corp;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import ar.com.laboratorio.steady.pollo_corp.registro.aplication.ClientRegistryUseCase;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class PolloCorpApplicationTests {
	@Autowired
	ClientRegistryUseCase clientRegistryUseCase;
	
	@Test
	void contextLoads() {
		assertThat(clientRegistryUseCase).isNotNull();
	}

}
