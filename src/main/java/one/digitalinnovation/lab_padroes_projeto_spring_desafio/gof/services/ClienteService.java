package one.digitalinnovation.lab_padroes_projeto_spring_desafio.gof.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import one.digitalinnovation.lab_padroes_projeto_spring_desafio.gof.entities.Cliente;
import one.digitalinnovation.lab_padroes_projeto_spring_desafio.gof.entities.Endereco;
import one.digitalinnovation.lab_padroes_projeto_spring_desafio.gof.repositories.ClienteRepository;
import one.digitalinnovation.lab_padroes_projeto_spring_desafio.gof.repositories.EnderecoRepository;
import one.digitalinnovation.lab_padroes_projeto_spring_desafio.gof.services.exceptions.DatabaseException;
import one.digitalinnovation.lab_padroes_projeto_spring_desafio.gof.services.exceptions.ResourceNotFoundException;
import one.digitalinnovation.lab_padroes_projeto_spring_desafio.gof.services.interfaces.ClienteServiceInterface;

@Service
public class ClienteService implements ClienteServiceInterface{
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private ViaCepService viaCepService;
	
	@Override
	public List<Cliente> buscarTodos() {
		return clienteRepository.findAll();
	}

	@Override
	public Cliente buscarPorId(Long id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		return cliente.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	@Override
	public Cliente inserir(Cliente cliente) {
		return salvarClienteComCep(cliente);
	}
	
	@Override
	public Cliente atualizar(Long id, Cliente cliente) {
		/*
		 *	Cliente clienteBD = clienteRepository.getReferenceById(id);
		 *  o método getRefereceById é mais eficiente , pois não vai ao banco de dados
		 *  diferente do findById.
		 *  Ele prepara o objeto monitorado para depois efetuar uma operação com o 
		 *  banco de dados 
		 */
		Cliente clienteBD = buscarPorId(id);
		clienteBD.setNome(cliente.getNome());
		
		// Busca/salva o endereço novo no banco antes de associar ao cliente monitorado
        if (cliente.getEndereco() != null && cliente.getEndereco().getCep() != null) {
            String cep = cliente.getEndereco().getCep();
            Endereco endereco = buscarOuCriarEndereco(cep);
            clienteBD.setEndereco(endereco);
        }
        
		return clienteRepository.save(clienteBD);		
	}

	@Override
	public void deletar(Long id) {
		try {
			Cliente cliente = buscarPorId(id);
			clienteRepository.delete(cliente);			
		}
		catch(DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	private Cliente salvarClienteComCep(Cliente cliente) {
		String cep = cliente.getEndereco().getCep();
		Endereco endereco = buscarOuCriarEndereco(cep);
		cliente.setEndereco(endereco);
		return clienteRepository.save(cliente);
	}

	 private Endereco buscarOuCriarEndereco(String cep) {
        return enderecoRepository.findById(cep).orElseGet(() -> {
            Endereco novoEndereco = viaCepService.consultarCep(cep);
            return enderecoRepository.save(novoEndereco);
        });
	 }
}
