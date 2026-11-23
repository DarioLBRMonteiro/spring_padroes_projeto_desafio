package one.digitalinnovation.lab_padroes_projeto_spring_desafio.gof.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import one.digitalinnovation.lab_padroes_projeto_spring_desafio.gof.entities.Endereco;


/**
 * Cliente HTTP, criado via <b>OpenFeign</b>, para o consumo da API do
 * <b>ViaCep</b>
 * 
 *  @see <a href="https://spring.io/projects/spring-cloud-openfeign">Spring Cloud OpenFeign</a>
 *  @see <a href="https://viacep.com.br">ViaCEP</a>
 *  
 *  @author dario
 */

@FeignClient(name = "viacep", url = "https://viacep.com.br/ws")
public interface ViaCepService {

	public static final String CEP2 = "cep";

	@GetMapping("/{cep}/json/")
	Endereco consultarCep(@PathVariable(CEP2) String cep);

}
