package br.com.farias.explorandomarte.server.application.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import com.sun.jersey.api.client.ClientResponse.Status;

import br.com.farias.explorandomarte.server.domain.service.ExplorandoMarteServiceImpl;
import br.com.farias.explorandomarte.server.domain.service.ExplorarMarteService;

@Path("/")
@Consumes("application/json")
@Produces("application/json; charset=UTF-8")
public class ExplorandoMarteResource {
	
	private static final String ERRO_DEFINIR_PLANALTO = "Erro ao definir o planalto. Parâmetros informados inválidos: ";
	private static final String ERRO_CONTROLAR_SONDA = "Erro ao controlar a sonda. Parâmetros informados inválidos: ";
	private ExplorarMarteService service = ExplorandoMarteServiceImpl.getInstance();
	
	@Path("/planalto")
	@POST
	public Response definirPlanalto(@QueryParam("limiteX") int limiteX, @QueryParam("limiteY") int limiteY) {
		if (limiteX <= 0 || limiteY <= 0) {
			return Response.status(Status.BAD_REQUEST).entity(ERRO_DEFINIR_PLANALTO + limiteX + " " + limiteY).build();
		}
		service.definirPlanalto(limiteX, limiteY);
		
		return Response.status(200).build();
	}
	
	@Path("/sondas/{posicaoX}/{posicaoY}/controlar")
	@POST
	public Response controlarSonda(@PathParam("posicaoX") int posicaoX, @PathParam("posicaoY") int posicaoY,
			@QueryParam("direcao") String direcao, @QueryParam("comandos") String comandos) {
		if (posicaoX >= 0 && posicaoY >= 0 && direcao.matches("[NSEW]") && comandos.matches("[LRM]+")) {
			char[] arrayComandos = comandos.toCharArray();
			service.controlarSonda(posicaoX, posicaoY, direcao.charAt(0), arrayComandos);
			return Response.status(200).build();
		} else {
			StringBuilder msgErro = new StringBuilder(ERRO_CONTROLAR_SONDA);
			msgErro.append("posicaoX=").append(posicaoX).append(",")
				.append("posicaoY=").append(posicaoY).append(",")
				.append("direcao=").append(direcao).append(",")
				.append("comandos=").append(comandos);
			return Response.status(Status.BAD_REQUEST).entity(msgErro).build();
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
