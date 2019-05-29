package store_ws;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ctrls.FocalizadoData;

@Path("/wsp")
public class ProducesWS {
	
	@GET
	@Produces({MediaType.APPLICATION_JSON + ";charset=UTF-8"})
	@Path("/listarFocalizados")
	public Response traerFocalizados(){
		SimpleDateFormat dfh = new SimpleDateFormat("HH:mm:ss");
		SimpleDateFormat dff = new SimpleDateFormat("dd/MM/yyyy");
		Date inicio = new Date();
		System.out.println("START: " 	+ dfh.format(inicio) + " hrs. " + dff.format(inicio) + "\n - INIT PROCESS");
		List<Object> data = new FocalizadoData().getCollection();
		Date fin = new Date();
		System.out.println("FINISHED: " + dfh.format(fin) + " hrs. " + dff.format(fin) + "\n - PROCESS COMPLETE");
		GenericEntity<List<Object>> entity = new GenericEntity<List<Object>>(data) {};
		Response response =	Response.status(201).entity(entity).build();
		return response;
	}

}
