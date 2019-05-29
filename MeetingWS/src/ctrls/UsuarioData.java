package ctrls;

import java.util.ArrayList;
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
import model.Usuario;
import utils.Main;

public class UsuarioData implements IData{

	public 	static final Logger logger		=	Logger.getLogger(UsuarioData.class);
	private static final String	DBNAME 		= 	"meeting";
	private static final String	COLLECTION 	= 	"usuario";
	
	public UsuarioData(){
		
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
		        	int 	idUsuario	=	doc.getInteger("IdUsuario");
		        	int 	idPersona 	=	doc.getInteger("IdPersona");
		        	String	username	=	doc.getString("Username");
		        	String	password_	=	doc.getString("Password");
		        	//SI EXISTE DATA CARGA LISTA
		        	data.add(new Usuario(idUsuario, idPersona, username, password_));
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
		        	Usuario  usuario			=	(Usuario) object;
		        	int		 idUsuario			=	new Main().countCollection(mongo, DBNAME, COLLECTION)
		        									+ usuario.getIdUsuario();
		        	int		 idPersona			=	new Main().countCollection(mongo, DBNAME, COLLECTION)
		        									+ usuario.getIdPersona();
		        	Document document			=	new Document();
		        	document.put("IdUsuario"	,	idUsuario);
		        	document.put("IdPersona"	,	idPersona);
		        	document.put("Username"		,	usuario.getUsername());
		        	document.put("Password"		,	usuario.getPassword());
			        docList.add(document);
		        }
		        
		        /***********************  INSERTA A TABLA  ***********************/
		        table.insertMany(docList);
		        
			}catch(Exception ex){
				logger.error(ex);
			}
		}else{
			logger.error("ERROR CONEXION MONGO_DB");
		}
		return ok;
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
				Document docFilters = new Document();
				for(Entry<String, Object> entry : set) {
					docFilters.append(entry.getKey(), entry.getValue());
				}
				
				//RECORRE LA COLLECTION Y TRAE SEGUN FILTRO ADJUNTO
				MongoCursor<Document> cursor = table.find(docFilters).iterator();
				while(cursor.hasNext()) {
					Document doc = cursor.next();
					doc.remove("_id");
					for(Entry<String, Object> entry : set) {
						doc.remove(entry.getKey());
					}
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

	
}
