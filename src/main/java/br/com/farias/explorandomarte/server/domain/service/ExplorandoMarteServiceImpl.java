package br.com.farias.explorandomarte.server.domain.service;

import java.util.Set;

import br.com.farias.explorandomarte.server.domain.exception.PosicaoInvalidaException;
import br.com.farias.explorandomarte.server.domain.model.DirecaoRosaDosVentos;
import br.com.farias.explorandomarte.server.domain.model.Lado;
import br.com.farias.explorandomarte.server.domain.model.Planalto;
import br.com.farias.explorandomarte.server.domain.model.Sonda;

/**
 * Serviço do domínio responsável por controlar as instâncias de planalto e sonda
 * e invocar os métodos nos objetos de domínio.
 * Utilizei o padrão Singleton levando em conta que só podem existir uma
 * única instância de planalto para serem controladas.
 * 
 * @author Rafael A. Farias
 *
 */
public class ExplorandoMarteServiceImpl implements ExplorarMarteService {
	
	private Planalto planalto;
	
	private static final ExplorandoMarteServiceImpl instance = new ExplorandoMarteServiceImpl();
	
	/**
	 * Construtor privado para implementar o padrão Singleton
	 */
	private ExplorandoMarteServiceImpl() {
		
	}
	
	public static final ExplorandoMarteServiceImpl getInstance() {
		return instance;
	}

	/**
	 * Obtêm a sonda pela posição x e y ou cria uma nova se necessário
	 * e executa todos os comandos de controle recebidos
	 * Marcado como synchronized para não permitir outras operações concorrentes
	 * na mesma sonda ou em outras sondas.
	 */
	@Override
	public synchronized void controlarSonda(int posicaoX, int posicaoY, char direcao, char... comandos) {
		if (posicaoX < 0 || posicaoY < 0) {
			throw new PosicaoInvalidaException(posicaoX, posicaoY);
		}
		DirecaoRosaDosVentos direcaoRosaDosVentos = DirecaoRosaDosVentos.fromChar(direcao);
		Sonda sonda = planalto.buscarSonda(posicaoX, posicaoX);
		//Adiciona somente se já não houver sonda nesta posição
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
				throw new IllegalArgumentException("O comando informado é inválido: comando=" + c);
			}
		}
	}

	/**
	 * Define um novo planalto conforme os parâmetros de limite ou
	 * reseta a instância atual para uma nova instância de planalto
	 * Marcado como synchronized para não permitir que o planalto seja
	 * redefinido enquanto operações de controle de sondas ainda estejam
	 * em execução.
	 */
	@Override
	public synchronized void definirPlanalto(int limiteX, int limiteY) {
		if (limiteX > 0 && limiteY > 0) {
			planalto = new Planalto(limiteX, limiteY);
		} else {
			throw new IllegalArgumentException("O parâmetro limiteX e limiteY são inválidos. limiteX=" + limiteX + ", limiteY=" + limiteY);
		}	
	}

	@Override
	public Set<Sonda> listarSondas() {
		return planalto.listarSondas();
	}

}
