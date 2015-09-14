package br.com.farias.explorandomarte.server.domain.exception;

public class DomainException extends RuntimeException {

	public static final String ERRO_DOMINIO = "Erro na camada de dom�nio. Regra de neg�cio violada.";
	
	public DomainException() {
		super(ERRO_DOMINIO);
	}
	
	public DomainException(String msg) {
		super(msg);
	}
}
