package one.digitalinnovation.lab_padroes_projeto_spring_desafio.gof.services.exceptions;

public class ResourceNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(Object id) {
		super("Resource not found. Id " + id);
	}
	
}
