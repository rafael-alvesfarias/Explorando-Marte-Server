package br.com.farias.explorandomarte.server.domain.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class DirecaoTest {
	
	@Test(expected = IllegalArgumentException.class)
	public void criarDirecaoComAnguloMenorQueZero() {
		new Direcao(-1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void criarDirecaoComAnguloMaiorQue359() {
		new Direcao(361);
	}
	
	@Test
	public void obterFatorDe0Graus() {
		Direcao d = new Direcao(0);
		
		assertEquals(1, (int) d.getFatorX());
		assertEquals(0, (int) d.getFatorY());
	}
	
	@Test
	public void obterFatorDe90Graus() {
		Direcao d = new Direcao(90);
		
		assertEquals(0, (int) d.getFatorX());
		assertEquals(1, (int) d.getFatorY());
	}
	
	@Test
	public void obterFatorDe180Graus() {
		Direcao d = new Direcao(180);
		
		assertEquals(-1, (int) d.getFatorX());
		assertEquals(0, (int) d.getFatorY());
	}
	
	@Test
	public void obterFatorDe270Graus() {
		Direcao d = new Direcao(270);
		
		assertEquals(0, (int) d.getFatorX());
		assertEquals(-1, (int) d.getFatorY());
	}
	
	@Test
	public void girar90Graus() {
		Direcao d = new Direcao(90);
		
		d.girar(90);
		
		assertEquals(180, d.getAngulo());
	}
	
	@Test(expected =  IllegalArgumentException.class)
	public void girarAnguloMaiorQuePermitido() {
		Direcao d = new Direcao(90);
		
		d.girar(270);
		
	}

}
