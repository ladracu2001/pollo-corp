package ar.com.laboratorio.steady.pollo_corp;

import org.springframework.boot.SpringApplication;

public class TestPolloCorpApplication {

	public static void main(String[] args) {
		SpringApplication.from(PolloCorpApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
