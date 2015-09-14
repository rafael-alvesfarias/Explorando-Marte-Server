package br.com.farias.explorandomarte.server.domain.exception;

public class DomainException extends RuntimeException {

	public static final String ERRO_DOMINIO = "Erro na camada de domínio. Regra de negócio violada.";
	
	public DomainException() {
		super(ERRO_DOMINIO);
	}
	
	public DomainException(String msg) {
		super(msg);
	}
}
