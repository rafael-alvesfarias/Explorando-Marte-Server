package br.com.farias.explorandomarte.server.domain.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class SondaTest {
	
	@Test
	public void moverSondaDuasPosicoesNorte() {
		Sonda s = new Sonda(0, 0, DirecaoRosaDosVentos.fromChar('S'));
		s.setPlanalto(new Planalto(5, 5));
		
		s.girar(Lado.R);
		s.girar(Lado.R);
		s.mover();
		s.mover();
		
		assertEquals(s.getPosicaoX(), 0);
		assertEquals(s.getPosicaoY(), 2);
	}
	
	@Test
	public void moverSondaDuasPosicoesSul() {
		Sonda s = new Sonda(0, 2, DirecaoRosaDosVentos.fromChar('N'));
		s.setPlanalto(new Planalto(5, 5));
		
		s.girar(Lado.L);
		s.girar(Lado.L);
		s.mover();
		s.mover();
		
		assertEquals(s.getPosicaoX(), 0);
		assertEquals(s.getPosicaoY(), 0);
	}
	
	@Test
	public void moverSondaDuasPosicoesLeste() {
		Sonda s = new Sonda(0, 0, DirecaoRosaDosVentos.fromChar('N'));
		s.setPlanalto(new Planalto(5, 5));
		
		s.girar(Lado.R);
		s.mover();
		s.mover();
		
		assertEquals(s.getPosicaoX(), 2);
		assertEquals(s.getPosicaoY(), 0);
	}
	
	@Test
	public void moverSondaDuasPosicoesOeste() {
		Sonda s = new Sonda(2, 0, DirecaoRosaDosVentos.fromChar('N'));
		s.setPlanalto(new Planalto(5, 5));
		
		s.girar(Lado.L);
		s.mover();
		s.mover();
		
		assertEquals(s.getPosicaoX(), 0);
		assertEquals(s.getPosicaoY(), 0);
	}
	
	@Test
	public void moverSondaEmPosicaoNaoDisponivel() {
		Sonda s = new Sonda(0, 3, DirecaoRosaDosVentos.fromChar('N'));
		s.setPlanalto(new Planalto(3, 3));
		
		s.mover();
		
		assertEquals(0, s.getPosicaoX());
		assertEquals(3, s.getPosicaoY());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void criarSondaComParametroDirecaoNulo() {
		new Sonda(0, 0, null);
	}
	
	@Test
	public void criarSondaSemParametroDirecao() {
		Sonda s = new Sonda(0, 0);
		assertEquals("N", s.getDirecao());
	}

}
