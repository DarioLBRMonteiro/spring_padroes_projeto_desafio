package one.digitalinnovation.lab_padroes_projeto_spring_desafio.gof.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import one.digitalinnovation.lab_padroes_projeto_spring_desafio.gof.entities.Cliente;
import one.digitalinnovation.lab_padroes_projeto_spring_desafio.gof.entities.Endereco;
import one.digitalinnovation.lab_padroes_projeto_spring_desafio.gof.repositories.ClienteRepository;
import one.digitalinnovation.lab_padroes_projeto_spring_desafio.gof.repositories.EnderecoRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;

	@Override
	public void run(String... args) throws Exception {
		Endereco e1 = new Endereco("20540230","Rua Guamerim","","","","","","","","","","","");
		Cliente c1 = new Cliente(null, "Dario", e1);
		enderecoRepository.save(e1);
		clienteRepository.saveAll(Arrays.asList(c1));
	}

}
