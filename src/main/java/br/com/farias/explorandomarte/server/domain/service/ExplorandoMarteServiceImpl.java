package br.com.farias.explorandomarte.server.domain.service;

import java.util.Set;

import br.com.farias.explorandomarte.server.domain.exception.PosicaoInvalidaException;
import br.com.farias.explorandomarte.server.domain.model.DirecaoRosaDosVentos;
import br.com.farias.explorandomarte.server.domain.model.Lado;
import br.com.farias.explorandomarte.server.domain.model.Planalto;
import br.com.farias.explorandomarte.server.domain.model.Sonda;

/**
 * Servi�o do dom�nio respons�vel por controlar as inst�ncias de planalto e sonda
 * e invocar os m�todos nos objetos de dom�nio.
 * Utilizei o padr�o Singleton levando em conta que s� podem existir uma
 * �nica inst�ncia de planalto para serem controladas.
 * 
 * @author Rafael A. Farias
 *
 */
public class ExplorandoMarteServiceImpl implements ExplorarMarteService {
	
	private Planalto planalto;
	
	private static final ExplorandoMarteServiceImpl instance = new ExplorandoMarteServiceImpl();
	
	/**
	 * Construtor privado para implementar o padr�o Singleton
	 */
	private ExplorandoMarteServiceImpl() {
		
	}
	
	public static final ExplorandoMarteServiceImpl getInstance() {
		return instance;
	}

	/**
	 * Obt�m a sonda pela posi��o x e y ou cria uma nova se necess�rio
	 * e executa todos os comandos de controle recebidos
	 * Marcado como synchronized para n�o permitir outras opera��es concorrentes
	 * na mesma sonda ou em outras sondas.
	 */
	@Override
	public synchronized void controlarSonda(int posicaoX, int posicaoY, char direcao, char... comandos) {
		if (posicaoX < 0 || posicaoY < 0) {
			throw new PosicaoInvalidaException(posicaoX, posicaoY);
		}
		DirecaoRosaDosVentos direcaoRosaDosVentos = DirecaoRosaDosVentos.fromChar(direcao);
		Sonda sonda = planalto.buscarSonda(posicaoX, posicaoX);
		//Adiciona somente se j� n�o houver sonda nesta posi��o
		if (sonda == null) {
			sonda = new Sonda(posicaoX, posicaoY, direcaoRosaDosVentos);
			sonda.setPlanalto(planalto);
			planalto.add(sonda);
		}
		
		for(char c: comandos) {
			switch (c) {
			case 'L':
				sonda.girar(Lado.L);
				break;
			case 'R':
				sonda.girar(Lado.R);
				break;
			case 'M':
				sonda.mover();
				break;
			default:
				throw new IllegalArgumentException("O comando informado � inv�lido: comando=" + c);
			}
		}
	}

	/**
	 * Define um novo planalto conforme os par�metros de limite ou
	 * reseta a inst�ncia atual para uma nova inst�ncia de planalto
	 * Marcado como synchronized para n�o permitir que o planalto seja
	 * redefinido enquanto opera��es de controle de sondas ainda estejam
	 * em execu��o.
	 */
	@Override
	public synchronized void definirPlanalto(int limiteX, int limiteY) {
		if (limiteX > 0 && limiteY > 0) {
			planalto = new Planalto(limiteX, limiteY);
		} else {
			throw new IllegalArgumentException("O par�metro limiteX e limiteY s�o inv�lidos. limiteX=" + limiteX + ", limiteY=" + limiteY);
		}	
	}

	@Override
	public Set<Sonda> listarSondas() {
		return planalto.listarSondas();
	}

}
