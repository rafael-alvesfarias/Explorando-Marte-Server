package br.com.farias.explorandomarte.server.domain.exception;

public class PosicaoInvalidaException extends DomainException {

	public PosicaoInvalidaException(int posicaoX, int posicaoY, int limiteX, int limiteY) {
		super("A posi��o informada � inv�lida: ["+ posicaoX +"," + posicaoY +
				"] limites: ["+ limiteX +","+ limiteY +"]" );
	}
}
