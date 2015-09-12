package br.com.farias.explorandomarte.server.domain.model;

import java.util.LinkedHashSet;
import java.util.Set;

public class Planalto {
	
	private int tamanhoX;
	
	private int tamanhoY;
	
	//Utilizado LinkdedHashSet para manter a ordem de inserção e não permitir duplicatas
	private final Set<Sonda> sondas = new LinkedHashSet<Sonda>();
	
	public Planalto(int tamanhoX, int tamanhoY) {
		this.tamanhoX = tamanhoX;
		this.tamanhoY = tamanhoY;
	}

	public boolean add(Sonda sonda) {
		if (posicaoValida(sonda.getPosicaoX(), sonda.getPosicaoY())) {
			if (posicaoDisponivel(sonda.getPosicaoX(), sonda.getPosicaoY())) {
				sondas.add(sonda);
				return true;
			} else {
				return false;
			}
		} else {
			//TODO throw new PosicaoNaoDisponivelException(sonda.getPosicaoX(), sonda.getPosicaoY());
			throw new RuntimeException();
		}
	}
	
	private boolean posicaoValida(int posicaoX, int posicaoY) {
		return posicaoX <= tamanhoX && posicaoY <= tamanhoY;
	}
	
	public boolean posicaoDisponivel(int posicaoX, int posicaoY) {
		return !sondas.contains(new Sonda(posicaoX, posicaoY));
	}
	
	public Set<Sonda> listarSondas() {
		return new LinkedHashSet<Sonda>(sondas);
	}

	public Sonda buscarSonda(int posicaoX, int posicaoY) {
		for(Sonda s: sondas) {
			if(s.getPosicaoX() == posicaoX && s.getPosicaoY() == posicaoY) {
				return s;
			}
		}
		return null;
	}

}
