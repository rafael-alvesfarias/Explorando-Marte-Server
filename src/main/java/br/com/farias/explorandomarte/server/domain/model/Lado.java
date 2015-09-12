package br.com.farias.explorandomarte.server.domain.model;

public enum Lado {
	L, //Esquerda
	R;  //Direita
	public static Lado fromChar(char lado) {
		switch (lado) {
			case 'l':
			case 'L': return L;
			case 'r':
			case 'R': return R;
			default:
				throw new IllegalArgumentException("Parâmetro lado inválido: " + lado);
			}
	}
}