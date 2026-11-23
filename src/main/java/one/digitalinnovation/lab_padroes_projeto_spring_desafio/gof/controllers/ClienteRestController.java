package one.digitalinnovation.lab_padroes_projeto_spring_desafio.gof.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import one.digitalinnovation.lab_padroes_projeto_spring_desafio.gof.entities.Cliente;
import one.digitalinnovation.lab_padroes_projeto_spring_desafio.gof.services.ClienteService;

/**
 * Esse {@link RestController} representa nossa <b>Facade</b>, pois abstrai toda
 * a complexidade de integrações (Banco de Dados H2 e API do ViaCEP) em uma
 * interface simples e coesa (API REST).
 * 
 * @author dario
 */
@RestController
@RequestMapping(value="/clientes")
public class ClienteRestController {

	@Autowired
	private ClienteService clienteService;

	@GetMapping
	public ResponseEntity<List<Cliente>> buscarTodos(){
		List<Cliente> list = clienteService.buscarTodos();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id){
		Cliente obj = clienteService.buscarPorId(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<Cliente> inserir(@RequestBody Cliente cliente){
		Cliente obj = clienteService.inserir(cliente);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		clienteService.deletar(id);
		return ResponseEntity.noContent().build();
		
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Cliente> atualizar(@PathVariable Long id, @RequestBody Cliente cliente){
		cliente = clienteService.atualizar(id, cliente);
		return ResponseEntity.ok().body(cliente);		
	}

}
