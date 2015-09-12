package br.com.farias.explorandomarte.server.domain.service;

import java.util.Set;

import br.com.farias.explorandomarte.server.domain.model.DirecaoRosaDosVentos;
import br.com.farias.explorandomarte.server.domain.model.Lado;
import br.com.farias.explorandomarte.server.domain.model.Planalto;
import br.com.farias.explorandomarte.server.domain.model.Sonda;

/**
 * Serviço do domínio responsável por controlar as instâncias de planalto e sonda
 * e invocar os métodos nos objetos de domínio.
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

	@Override
	public void controlarSonda(int posicaoX, int posicaoY, char direcao, char... comandos) {
		Sonda sonda = planalto.buscarSonda(posicaoX, posicaoX);
		//Adiciona somente se já não houver sonda nesta posição
		if (sonda == null) {
			sonda = new Sonda(posicaoX, posicaoY, DirecaoRosaDosVentos.fromChar(direcao));
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
				break;
			}
		}
	}

	@Override
	public void definirPlanalto(int limiteX, int limiteY) {
		planalto = new Planalto(limiteX, limiteY);		
	}

	@Override
	public Set<Sonda> listarSondas() {
		return planalto.listarSondas();
	}

}
