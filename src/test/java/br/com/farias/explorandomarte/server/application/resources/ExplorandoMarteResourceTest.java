package br.com.farias.explorandomarte.server.application.resources;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Response;

import static org.junit.Assert.*;
import mockit.Expectations;
import mockit.Mocked;
import mockit.Tested;
import mockit.integration.junit4.JMockit;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.farias.explorandomarte.server.domain.model.DirecaoRosaDosVentos;
import br.com.farias.explorandomarte.server.domain.model.Sonda;
import br.com.farias.explorandomarte.server.domain.service.ExplorandoMarteServiceImpl;

@RunWith(JMockit.class)
public class ExplorandoMarteResourceTest {
	
	@Tested
	private ExplorandoMarteResource resource;
	
	@Mocked
	private ExplorandoMarteServiceImpl service;
	
	@Test
	public void testDefinirPlanaltoComParametroInvalido() {
		Response response = resource.definirPlanalto(0, 0);
		assertEquals(400, response.getStatus()); response.getStatus();
		response = resource.definirPlanalto(-1, -1);
		assertEquals(400, response.getStatus()); response.getStatus();
	}
	
	@Test
	public void testDefinirPlanaltoSucesso(){
		new Expectations() {{
			service.definirPlanalto(anyInt, anyInt); minTimes = 1;
		}};
		Response response = resource.definirPlanalto(5, 5);
		assertEquals(200, response.getStatus()); response.getStatus();
	}
	
	@Test
	public void testControlarSondaComPosicaoInvalido() {
		Response response = resource.controlarSonda(-1, 0, "N", "LM");
		assertEquals(400, response.getStatus()); response.getStatus();
		response = resource.controlarSonda(0, -1, "N", "LM");
		assertEquals(400, response.getStatus()); response.getStatus();
	}
	
	@Test
	public void testControlarSondaComDirecaoInvalida() {
		Response response = resource.controlarSonda(0, 0, "Z", "LM");
		assertEquals(400, response.getStatus()); response.getStatus();
		response = resource.controlarSonda(0, 0, "A", "LM");
		assertEquals(400, response.getStatus()); response.getStatus();
	}
	
	@Test
	public void testControlarSondaSucesso() {
		new Expectations() {{
			service.controlarSonda(anyInt, anyInt, 'N', "LMRLMRLMRLMM".toCharArray()); minTimes = 1;
		}};
		Response response = resource.controlarSonda(0, 0, "N", "LMRLMRLMRLMM");
		assertEquals(200, response.getStatus()); response.getStatus();
	}
	
	@Test
	public void testControlarSondaComComandosInvalidos() {
		Response response = resource.controlarSonda(0, 0, "N", "AB");
		assertEquals(400, response.getStatus()); response.getStatus();
		response = resource.controlarSonda(0, 0, "N", "XZ");
		assertEquals(400, response.getStatus()); response.getStatus();
	}
	
	@Test
	public void testListarSonda() {
		Set<Sonda> sondas = new HashSet<Sonda>();
		sondas.add(new Sonda(1, 2, DirecaoRosaDosVentos.fromChar('E')));
		new Expectations() {{
			service.listarSondas(); result = sondas; minTimes = 1;
		}};

		JSONObject jsonObject = new JSONObject(resource.listarSondas());
		assertTrue(jsonObject.has("sondas"));
		assertEquals(1, jsonObject.getJSONArray("sondas").getJSONObject(0).getInt("posicaoX"));
		assertEquals(2, jsonObject.getJSONArray("sondas").getJSONObject(0).getInt("posicaoY"));
		assertEquals("E", jsonObject.getJSONArray("sondas").getJSONObject(0).getString("direcao"));
	}
}
