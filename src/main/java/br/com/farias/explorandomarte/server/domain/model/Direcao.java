package br.com.farias.explorandomarte.server.domain.model;

/**
 * Classe que representa uma dire��o que uma sonda est� apontando.
 * Prov� a funcionalidade de girar uma sonda e auxilia na movimenta��o.
 * Desenvolvida de maneira a permitir giros de 360 graus e permitir assim
 * maior flexibilidade para futuras melhorias.
 * @author Rafael
 *
 */
public class Direcao {

	/**
	 * Valor usado para calcular a movimenta��o no eixo X da sonda de acordo com o �ngulo;
	 */
	private double fatorX;
	
	/**
	 * Valor usado para calcular a movimenta��o no eixo Y da sonda de acordo com o �ngulo;
	 */
	private double fatorY;
	
	private int angulo;
	
	public Direcao(int angulo) {
		if(angulo < 0 || angulo > 359) {
			throw new IllegalArgumentException("O par�metro �ngulo � inv�lido: " + angulo);
		}
		this.angulo = angulo;
		
		calcularFator(angulo);
	}
	
	public void girar(int graus) {
		int novoAngulo = angulo + graus;
		if(novoAngulo < 0 || novoAngulo > 359) {
			throw new IllegalArgumentException("O par�metro graus � maior que o permitido: " + graus);
		}
		this.angulo = novoAngulo;
		calcularFator(novoAngulo);
	}
	
	/**
	 * Calcula o fator de x e y de acordo com as fun��es consseno e seno respectivamente
	 * @param angulo
	 */
	private void calcularFator(int angulo) {
		//Calculo do fator X de acordo com a fun��o cosseno
		this.fatorX = Math.cos(Math.toRadians(angulo));
		
		//Calculo do fator Y de acordo com a fun��o seno
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