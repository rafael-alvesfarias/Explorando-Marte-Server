package br.com.farias.explorandomarte.server.domain.model;

import org.junit.Test;

import br.com.farias.explorandomarte.server.domain.exception.PosicaoInvalidaException;
import static org.junit.Assert.*;

public class PlanaltoTest {
	
	@Test(expected = IllegalArgumentException.class)
	public void criarPlanaltoComTamanhoXInvalido() {
		new Planalto(-1, 1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void criarPlanaltoComTamanhoYInvalido() {
		new Planalto(1, -1);
	}
	
	@Test
	public void adicionarSonda() {
		Planalto p = new Planalto(2, 2);
		
		p.add(new Sonda(2, 2));
		
		assertEquals(1, p.listarSondas().size());
		assertTrue(p.listarSondas().contains(new Sonda(2, 2)));
	}
	
	@Test
	public void adicionarDuasSondasNaMesmaPosicao() {
		Planalto p = new Planalto(2, 2);
		
		p.add(new Sonda(2, 2));
		p.add(new Sonda(2, 2));
		
		assertEquals(1, p.listarSondas().size());
		assertTrue(p.listarSondas().contains(new Sonda(2, 2)));
	}
	
	@Test(expected = PosicaoInvalidaException.class)
	public void adicionarSondaEmPosicaoInvalida() {
		Planalto p = new Planalto(2, 2);
		
		p.add(new Sonda(3, 3));
	}
	
	@Test
	public void posicaoValida() {
		Planalto p = new Planalto(2, 2);
		
		assertTrue(p.posicaoValida(0, 0));
		assertTrue(p.posicaoValida(1, 0));
		assertTrue(p.posicaoValida(0, 1));
		assertTrue(p.posicaoValida(1, 1));
		assertTrue(p.posicaoValida(2, 1));
		assertTrue(p.posicaoValida(1, 2));
		assertTrue(p.posicaoValida(2, 2));
		assertFalse(p.posicaoValida(3, 2));
		assertFalse(p.posicaoValida(2, 3));
	}

}
