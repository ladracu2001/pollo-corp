package ar.com.laboratorio.steady.pollo_corp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class PolloCorpApplicationTests {

	@Test
	void contextLoads() {
	}

}
