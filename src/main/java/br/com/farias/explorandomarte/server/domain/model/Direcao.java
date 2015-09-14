package br.com.farias.explorandomarte.server.domain.model;

/**
 * Classe que representa uma direção que uma sonda está apontando.
 * Provê a funcionalidade de girar uma sonda e auxilia na movimentação.
 * Desenvolvida de maneira a permitir giros de 360 graus e permitir assim
 * maior flexibilidade para futuras melhorias.
 * @author Rafael
 *
 */
public class Direcao {

	/**
	 * Valor usado para calcular a movimentação no eixo X da sonda de acordo com o ângulo;
	 */
	private double fatorX;
	
	/**
	 * Valor usado para calcular a movimentação no eixo Y da sonda de acordo com o ângulo;
	 */
	private double fatorY;
	
	private int angulo;
	
	public Direcao(int angulo) {
		if(angulo < 0 || angulo > 359) {
			throw new IllegalArgumentException("O parâmetro ângulo é inválido: " + angulo);
		}
		this.angulo = angulo;
		
		calcularFator(angulo);
	}
	
	public void girar(int graus) {
		int novoAngulo = angulo + graus;
		if(novoAngulo < 0 || novoAngulo > 359) {
			throw new IllegalArgumentException("O parâmetro graus é maior que o permitido: " + graus);
		}
		this.angulo = novoAngulo;
		calcularFator(novoAngulo);
	}
	
	/**
	 * Calcula o fator de x e y de acordo com as funções consseno e seno respectivamente
	 * @param angulo
	 */
	private void calcularFator(int angulo) {
		//Calculo do fator X de acordo com a função cosseno
		this.fatorX = Math.cos(Math.toRadians(angulo));
		
		//Calculo do fator Y de acordo com a função seno
		this.fatorY = Math.sin(Math.toRadians(angulo));
	}
	
	public double getFatorX() {
		return fatorX;
	}
	
	public double getFatorY() {
		return fatorY;
	}

	public int getAngulo() {
		return angulo;
	}
}