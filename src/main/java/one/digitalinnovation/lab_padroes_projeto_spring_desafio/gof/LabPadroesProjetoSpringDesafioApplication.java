package one.digitalinnovation.lab_padroes_projeto_spring_desafio.gof;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class LabPadroesProjetoSpringDesafioApplication {

	public static void main(String[] args) {
		SpringApplication.run(LabPadroesProjetoSpringDesafioApplication.class, args);
	}

}
