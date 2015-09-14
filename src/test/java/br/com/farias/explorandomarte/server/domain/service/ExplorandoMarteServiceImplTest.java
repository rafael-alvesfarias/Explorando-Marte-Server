package br.com.farias.explorandomarte.server.domain.service;

import java.util.LinkedHashSet;
import java.util.Set;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Mocked;
import mockit.NonStrictExpectations;
import mockit.Tested;
import mockit.integration.junit4.JMockit;

import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

import br.com.farias.explorandomarte.server.domain.exception.PosicaoInvalidaException;
import br.com.farias.explorandomarte.server.domain.model.Lado;
import br.com.farias.explorandomarte.server.domain.model.Planalto;
import br.com.farias.explorandomarte.server.domain.model.Sonda;

@RunWith(JMockit.class)
public class ExplorandoMarteServiceImplTest {
	
	@Tested
	private ExplorandoMarteServiceImpl service;
	
	@Injectable
	private Planalto planalto;
	
	@Test(expected = IllegalArgumentException.class)
	public void definirPlanaltoComParametrosNegativos() {
		service.definirPlanalto(-1, -1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void definirPlanaltoComParametrosZerados() {
		service.definirPlanalto(0, 0);
	}
	
	@Test
	public void testDefinirPlanalto(@Mocked Planalto planalto) {
		new Expectations() {{
			new Planalto(1, 1); minTimes = 1;
		}};
		service.definirPlanalto(1, 1);
	}
	
	@Test(expected = PosicaoInvalidaException.class)
	public void testControlarSondaComPosicaoInvalida(){
		service.controlarSonda(-1, -1, 'N', new char[]{'R'});
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testControlarSondaComDirecaoInvalida(){
		service.controlarSonda(0, 0, 'F', new char[]{'R'});
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testControlarSondaComComandoInvalido(){
		service.controlarSonda(0, 0, 'S', new char[]{'L','M','L','M','L','M','V'});
	}
	
	@Test
	public void testControlarSonda() {
		new NonStrictExpectations() {
			{
				planalto.buscarSonda(2, 2); minTimes = 1;
				result = null;
				Sonda s = new Sonda(2, 2); minTimes = 1;
				planalto.add(s); minTimes = 1;
				s.girar(Lado.L); minTimes = 1;
				s.girar(Lado.R); minTimes = 1;
				s.mover(); minTimes = 1;
			}
		};
		
		service.controlarSonda(2, 2, 'N', new char[]{'L','M','R'});
	}
	
	@Test
	public void testListarSondas() {
		Set<Sonda> setEsperado = new LinkedHashSet<Sonda>();
		Sonda s = new Sonda(1, 1);
		setEsperado.add(s);
		new Expectations() {{
			planalto.listarSondas(); result = setEsperado;
		}};
		
		Set<Sonda> setObtido = service.listarSondas();
		
		assertEquals(setEsperado, setObtido);
		
	}
	
}
