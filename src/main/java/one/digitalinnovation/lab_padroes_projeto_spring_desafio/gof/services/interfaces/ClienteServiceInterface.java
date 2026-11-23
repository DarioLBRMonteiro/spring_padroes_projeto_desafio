package one.digitalinnovation.lab_padroes_projeto_spring_desafio.gof.services.interfaces;

import java.util.List;

import one.digitalinnovation.lab_padroes_projeto_spring_desafio.gof.entities.Cliente;

public interface ClienteServiceInterface {
	List<Cliente> buscarTodos();
	Cliente buscarPorId(Long id);
	Cliente inserir(Cliente cliente);
	Cliente atualizar(Long id, Cliente cliente);
	void deletar(Long id);
}
