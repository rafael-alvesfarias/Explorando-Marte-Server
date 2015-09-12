package br.com.farias.explorandomarte.server.domain.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class Sonda {

	@XmlElement
	private int posicaoX;

	@XmlElement
	private int posicaoY;

	private DirecaoRosaDosVentos direcao;
	
	private Planalto planalto;
	
	public Sonda(){
		
	}

	public Sonda(int posicaoX, int posicaoY, DirecaoRosaDosVentos direcao) {
		this.posicaoX = posicaoX;
		this.posicaoY = posicaoY;
		this.direcao = direcao;
	}
	
	public Sonda(int posicaoX, int posicaoY) {
		this.posicaoX = posicaoX;
		this.posicaoY = posicaoY;
		this.direcao = DirecaoRosaDosVentos.N;
	}

	public void mover() {
		int nx = posicaoX + (int) (1 * direcao.getFatorX());
		int ny = posicaoY + (int) (1 * direcao.getFatorY());
		
		//Se move somente a posi��o estiver dispon�vel
		if (planalto != null && planalto.posicaoDisponivel(nx, ny)) {
			posicaoX = nx;
			posicaoY = ny;
		}
	}

	public void girar(Lado lado) {
		if (lado.equals(Lado.R)) {
			// girar para Direita
			direcao.proxima();
		} else {
			// girar para Esquerda
			direcao.anterior();
		}
	}

	public int getPosicaoX() {
		return posicaoX;
	}

	public int getPosicaoY() {
		return posicaoY;
	}
	
	@XmlElement
	public String getDirecao() {
		return direcao.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + posicaoX;
		result = prime * result + posicaoY;
		return result;
	}

	// Considera iguais duas sondas com a mesma posi��o X e Y
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sonda other = (Sonda) obj;
		if (posicaoX != other.posicaoX)
			return false;
		if (posicaoY != other.posicaoY)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return posicaoX + " " + posicaoY + " " + direcao;
	}

	public void setPlanalto(Planalto planalto) {
		this.planalto = planalto;
	}

}