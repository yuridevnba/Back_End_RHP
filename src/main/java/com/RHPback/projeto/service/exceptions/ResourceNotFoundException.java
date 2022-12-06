package com.RHPback.projeto.service.exceptions;

public class ResourceNotFoundException extends RuntimeException{


	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(Object id) { // id do objeto que foi pesquisado e não foi encontrado (RecursoNãoEncontradoException)
		super("Resource not found. Id "+id);
	}
	
}
