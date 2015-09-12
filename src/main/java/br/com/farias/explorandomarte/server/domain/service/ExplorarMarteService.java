package br.com.farias.explorandomarte.server.domain.service;

import java.util.Set;

import br.com.farias.explorandomarte.server.domain.model.Sonda;

public interface ExplorarMarteService {

	public void controlarSonda(int posicaoX, int posicaoY, char direcao, char... comandos);
	
	public void definirPlanalto(int limiteX, int limiteY);

	public Set<Sonda> listarSondas();
	
}
