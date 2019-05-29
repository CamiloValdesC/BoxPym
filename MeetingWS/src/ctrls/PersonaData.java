package ctrls;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;
import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.WriteResult;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import dataBase.MongoCliente;
import model.Persona;
import utils.Main;

public class PersonaData implements IData{
	
	public 	static final Logger logger		=	Logger.getLogger(PersonaData.class);
	private static final String	DBNAME 		= 	"meeting";
	private static final String	COLLECTION 	= 	"persona";
	
	public PersonaData(){
		
	}

	@Override
	public List<Object> getCollection() {
		List<Object> 	data			= 	new ArrayList<>();
		MongoClient 	mongo			=	MongoCliente.crearConexion();
		
		if(null != mongo){
			try{
				//SI NO EXISTE "BASE DE DATOS" LA CREA
				MongoDatabase db = mongo.getDatabase(DBNAME);
				
				//SI NO EXISTE "COLLECTION" LA CREA
				MongoCollection<Document> table = db.getCollection(COLLECTION);
				
		        //FindIterable
		        logger.debug("FindIterable");
		        FindIterable<Document> docs = table.find();
		        for(Document doc : docs){
		        	int 	idPersona 	=	doc.getInteger("IdPersona");
		        	int 	runPersona	=	doc.getInteger("Run");
		        	String	dv			=	doc.getString("Dv");
		        	String	nombre		=	doc.getString("Nombre");
		        	String	apellido	=	doc.getString("Apellido");
		        	//SI EXISTE DATA CARGA LISTA
		        	data.add(new Persona(idPersona, runPersona, dv, nombre, apellido));
		        }
		        
		        //FOR DIRECTO
	            logger.debug("ForDirecto");
	            for(Document cur : table.find()) {
	            	logger.debug(cur.toJson());
	            	int 	idPersona 	=	cur.getInteger("IdPersona");
		        	int 	runPersona	=	cur.getInteger("Run");
		        	String	dv			=	cur.getString("Dv");
		        	String	nombre		=	cur.getString("Nombre");
		        	String	apellido	=	cur.getString("Apellido");
		        	logger.debug(idPersona + " / " + runPersona + " / " + dv + " / " + nombre + " / " + apellido);
		        }
		        
		        //MongoCursor
		        logger.debug("MongoCursor");
		        MongoCursor<Document> iterator 	= 	table.find().iterator();
		        try{
			        while(iterator.hasNext()){
		            	logger.debug(iterator.next().toJson());
		            };
		        }finally{
		        	iterator.close();
		        }
		        		        
		        
			}catch(Exception ex){
				logger.error(ex);
			}
		}else{
			logger.error("ERROR CONEXION MONGO_DB");
		}

		return data;
	}

	@Override
	public List<Document> getCollectionFind(Set<Entry<String, Object>> set) {
		List<Document> 	docList	=	new ArrayList<Document>();
		MongoClient 	mongo	=	MongoCliente.crearConexion();
		
		if(null != mongo){
			try{				
				//SI NO EXISTE "BASE DE DATOS" LA CREA
				MongoDatabase db = mongo.getDatabase(DBNAME);
				
				//SI NO EXISTE "COLLECTION" LA CREA
				MongoCollection<Document>  table = db.getCollection(COLLECTION);
				
				//CREAR FILTRO CAMPOS (WHERE) "Set Entry"
				
				//Bson	bson	=	null;
				//for(Entry<String, String> entry : set) {
				//		bson 	= 	Filters.eq(entry.getKey(), entry.getValue());
				//}
				
				Document docFilters = new Document();
				for(Entry<String, Object> entry : set) {
					docFilters.append(entry.getKey(), entry.getValue());
				}
				
				//Document 	doc 	=	table.find(bson).filter(bson).first();
				//doc.remove("_id");
				//docList.add(doc);
				
				//Document 	docF 	= 	new Document()
				//						.append	("Nombre", "Gabriel")			
				//						.append("Run", 24014255);
				
				//RECORRE LA COLLECTION Y TRAE SEGUN FILTRO ADJUNTO
				MongoCursor<Document> cursor = table.find(docFilters).iterator();
				while(cursor.hasNext()) {
					Document doc = cursor.next();
					doc.remove("_id");
					docList.add(doc);
				}
								
			}catch(Exception ex){
				logger.error(ex);
			}
		}else{
			logger.error("ERROR CONEXION MONGO_DB");
		}

		return docList;
	}

	@Override
	public WriteResult insertNewDocCollection(List<Object> lista) {
		WriteResult ok 		=	null;
		MongoClient mongo	=	MongoCliente.crearConexion();
		if(mongo != null){
			try{
				//SI NO EXISTE "BASE DE DATOS" LA CREA
				MongoDatabase db = mongo.getDatabase(DBNAME);
		        
				//SI NO EXISTE "COLLECTION" LA CREA
				MongoCollection<Document> table = db.getCollection(COLLECTION);
		        
		        /********************  CREA OBJECTOS BASICOS  ********************/
				List<Document> docList = new ArrayList<>();
		        for(Object object : lista){
		        	Persona  persona			=	(Persona) object;
		        	int		 idPersona			=	new Main().countCollection(mongo, DBNAME, COLLECTION)
		        									+ persona.getIdPersona();
		        	Document document			=	new Document();
		        	document.put("IdPersona"	,	idPersona);
		        	document.put("Run"			,	persona.getRun());
		        	document.put("Dv"			,	persona.getDv());
		        	document.put("Nombre"		,	persona.getNombre());
		        	document.put("Apellido"		,	persona.getApellido());
			        //FORMATO FECHA - HORA
			        SimpleDateFormat df_1 	= 	new SimpleDateFormat("yyyy-MM-dd");
			        SimpleDateFormat df_2 	=	new SimpleDateFormat("HH:mm:ss");
			        Date date = new Date();
			        document.put("FechaRegistro",	df_1.format(date));
			        document.put("HoraRegistro"	,	df_2.format(date));
			        docList.add(document);			        
		        }
		        
		        //INSERTAR EN LISTA
		        table.insertMany(docList);
		        
			}catch(Exception ex){
				logger.error(ex);
			}
		}else{
			logger.error("ERROR CONEXION MONGO_DB");
		}
		return ok;
	}
	
}
