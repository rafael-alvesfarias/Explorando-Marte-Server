package br.com.farias.explorandomarte.server.domain.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class DirecaoRosaDosVentosTest {

	@Test(expected = IllegalArgumentException.class)
	public void criarDirecaoComDirecaoInvalidaChar() {
		DirecaoRosaDosVentos.fromChar('U');
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void criarDirecaoComDirecaoInvalidaString() {
		DirecaoRosaDosVentos.fromString("V");
	}
	
	@Test
	public void girarDirecao60Graus(){
		Direcao d = DirecaoRosaDosVentos.fromChar('N');
		d.girar(60);
		assertEquals("N", d.toString());
	}
	
	@Test
	public void girarDirecao30Graus(){
		Direcao d = DirecaoRosaDosVentos.fromChar('N');
		d.girar(30);
		assertEquals("N", d.toString());
	}
	
	@Test
	public void girarDirecao90Graus(){
		Direcao d = DirecaoRosaDosVentos.fromChar('E');
		d.girar(90);
		assertEquals("N", d.toString());
	}
	
	@Test
	public void girarDirecao180Graus(){
		Direcao d = DirecaoRosaDosVentos.fromChar('E');
		d.girar(180);
		assertEquals("W", d.toString());
	}
	
	@Test
	public void girarDirecao270Graus(){
		Direcao d = DirecaoRosaDosVentos.fromChar('E');
		d.girar(270);
		assertEquals("S", d.toString());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void girarDirecaoParaUmValorNaoPermitido(){
		Direcao d = DirecaoRosaDosVentos.fromChar('N');
		d.girar(270);
	}
	
	@Test
	public void proximaDirecao() {
		DirecaoRosaDosVentos d = DirecaoRosaDosVentos.fromChar('E');
		d.proxima();
		assertEquals("S", d.toString());
		d.proxima();
		assertEquals("W", d.toString());
		d.proxima();
		assertEquals("N", d.toString());
		d.proxima();
		assertEquals("E", d.toString());
	}
	
	@Test
	public void direcaoAnterior() {
		DirecaoRosaDosVentos d = DirecaoRosaDosVentos.fromChar('E');
		d.anterior();
		assertEquals("N", d.toString());
		d.anterior();
		assertEquals("W", d.toString());
		d.anterior();
		assertEquals("S", d.toString());
		d.anterior();
		assertEquals("E", d.toString());
	}
}
