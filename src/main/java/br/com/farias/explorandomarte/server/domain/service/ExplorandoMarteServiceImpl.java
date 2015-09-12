package br.com.farias.explorandomarte.server.domain.service;

import java.util.Set;

import br.com.farias.explorandomarte.server.domain.model.DirecaoRosaDosVentos;
import br.com.farias.explorandomarte.server.domain.model.Lado;
import br.com.farias.explorandomarte.server.domain.model.Planalto;
import br.com.farias.explorandomarte.server.domain.model.Sonda;

/**
 * Servi�o do dom�nio respons�vel por controlar as inst�ncias de planalto e sonda
 * e invocar os m�todos nos objetos de dom�nio.
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

	@Override
	public void controlarSonda(int posicaoX, int posicaoY, char direcao, char... comandos) {
		Sonda sonda = planalto.buscarSonda(posicaoX, posicaoX);
		//Adiciona somente se j� n�o houver sonda nesta posi��o
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
