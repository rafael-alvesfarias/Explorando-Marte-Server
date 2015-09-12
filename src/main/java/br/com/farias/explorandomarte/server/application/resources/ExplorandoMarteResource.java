package br.com.farias.explorandomarte.server.application.resources;

import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.farias.explorandomarte.server.domain.model.Sonda;
import br.com.farias.explorandomarte.server.domain.service.ExplorandoMarteServiceImpl;
import br.com.farias.explorandomarte.server.domain.service.ExplorarMarteService;

@Path("/")
@Consumes("application/json")
@Produces("application/json; charset=UTF-8")
public class ExplorandoMarteResource {
	
	private ExplorarMarteService service = ExplorandoMarteServiceImpl.getInstance();
	
	@Path("/planalto")
	@POST
	public Response definirPlanalto(@QueryParam("limiteX") int limiteX, @QueryParam("limiteY") int limiteY) {
		service.definirPlanalto(limiteX, limiteY);
		
		return Response.status(200).build();
	}
	
	@Path("/sondas/{posicaoX}/{posicaoY}/controlar")
	@POST
	public Response controlarSonda(@PathParam("posicaoX") int posicaoX, @PathParam("posicaoY") int posicaoY,
			@QueryParam("direcao") String direcao, @QueryParam("comandos") String comandos) {
		if (comandos.matches("[LRM]+")) {
			char[] arrayComandos = comandos.toCharArray();
			service.controlarSonda(posicaoX, posicaoY, direcao.charAt(0), arrayComandos);
			return Response.status(200).build();
		} else {
			return Response.status(500).build();
		}
	}
	
	@Path("/sondas")
	@GET
	public String listarSondas() {
		JSONObject retornoJson = new JSONObject();
		retornoJson.put("sondas", service.listarSondas());
		
		return retornoJson.toString();
	}
	

}
