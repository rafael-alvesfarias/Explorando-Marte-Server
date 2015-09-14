package br.com.farias.explorandomarte.server.domain.model;

/**
 * Subclasse da classe Direcao responsável por permitir apenas Direções de uma Rosa dos ventos. N E S W
 * @author Rafael A. Farias
 *
 */
public class DirecaoRosaDosVentos extends Direcao {
	
	private DirecaoRosaDosVentos(int angulo) {
		super(angulo);
	}
	
	/**
	 * Fábrica estática de direções de uma rosa dos ventos (N, E, S, W)
	 * @param direcao 'N', 'E', 'S', 'W'
	 * @return
	 */
	public static DirecaoRosaDosVentos fromChar(char direcao) {
		switch(direcao) {
			case 'n':
			case 'N': return new DirecaoRosaDosVentos(90);
			case 'e':
			case 'E': return new DirecaoRosaDosVentos(0);
			case 's':
			case 'S': return new DirecaoRosaDosVentos(270);
			case 'w':
			case 'W': return new DirecaoRosaDosVentos(180);
			default: throw new IllegalArgumentException("Parãmetro direção inválido: " + direcao);
		}
	}
	
	/**
	 * Fábrica estática de direções de uma rosa dos ventos (N, E, S, W)
	 * @param direcao 'N', 'E', 'S', 'W'
	 * @return
	 */
	public static DirecaoRosaDosVentos fromString(String direcao) {
		return fromChar(direcao.charAt(0));
	}
	
	
	/**
	 * Sobrescrito para somente permitir girar ângulos multiplos de 90 graus
	 */
	@Override
	public void girar(int graus) {
		if(graus % 90 == 0) {
			super.girar(graus);
		}
	}
	
	public void proxima() {
		if(getAngulo() == 0) {
			girar(270);
		} else {
			girar(-90);
		}
	}
	
	public void anterior() {
		if(getAngulo() == 270) {
			girar(-270);
		} else {
			girar(90);
		}
	}
	
	@Override
	public String toString() {
		switch (getAngulo()) {
			case 90: return "N";
			case 0: return "E";
			case 270: return "S";
			case 180: return "W";
			default: return null;
		}

	}
}
