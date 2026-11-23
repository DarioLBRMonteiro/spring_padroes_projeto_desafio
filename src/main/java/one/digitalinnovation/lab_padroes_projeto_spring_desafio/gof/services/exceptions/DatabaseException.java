package one.digitalinnovation.lab_padroes_projeto_spring_desafio.gof.services.exceptions;

public class DatabaseException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public DatabaseException(String msg) {
		super(msg);
	}
	

}
