package store_ws;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.bson.Document;

import ctrls.FocalizadoData;
import ctrls.PersonaData;
import ctrls.UsuarioData;
import utils.Main;

@Path("/wsp")
@Produces({MediaType.APPLICATION_JSON + ";charset=UTF-8"})
public class ProducesWS {
	
	/************************************************** PERSONAS **************************************************/
	
	@GET	
	@Path("/listarPersonas")
	public Response traerPersonas(){
		List<Object> data = new PersonaData().getCollection();
		GenericEntity<List<Object>> entity = new GenericEntity<List<Object>>(data) {};
		Response response =	Response.status(201).entity(entity).build();		
		return response;
	}
	
	@GET	
	@Path("/traerPersona")
	public Response traerPersona(	@QueryParam("run") int 	  run,
									@QueryParam("nom") String nom){
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("Run"	, run);
		map.put("Nombre", nom);
		Set<Entry<String, Object>> set = map.entrySet();
		
		List<Document> data = new PersonaData().getCollectionFind(set);
		GenericEntity<List<Document>> entity = new GenericEntity<List<Document>>(data) {};
		Response response =	Response.status(201).entity(entity).build();		
		return response;
	}
	
	/************************************************** USUARIOS **************************************************/
	
	@GET	
	@Path("/listarUsuarios")
	public Response traerUsuarios(){
		List<Object> data	= 	new UsuarioData().getCollection();
		Response response 	= 	Response.status(201).entity(data).build();		
		return response;
	}
	
	@GET	
	@Path("/traerUsuario")
	public Response traerUsuario(	@QueryParam("username") String username,
									@QueryParam("password") String password){
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("Username", username);
		map.put("Password", password);
		Set<Entry<String, Object>> set = map.entrySet();
		
		List<Document> data = new UsuarioData().getCollectionFind(set);
		GenericEntity<List<Document>> entity = new GenericEntity<List<Document>>(data) {};
		Response response =	Response.status(201).entity(entity).build();		
		return response;
	}
		
	/***************************************************************************************************************/
	
	@GET
	@Path("/test")
	public Response test(){
		Main.insertarData();
		List<String> status = new ArrayList<>();
		status.add("ok");
		Response response 	= 	Response.status(201).entity(status).build();		
		return response;
	}
	
	/************************************************* FOCALIZADOS *************************************************/
	
	@GET	
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
