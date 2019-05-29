package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import db_connect.MongoCliente;


public class Main {
	
	public 	static final Logger logger = Logger.getLogger(Main.class);

	/**********************************************************************************************************************************************/	

	public static void deleteCollection(String db_name){
		
		MongoClient mongo	=	MongoCliente.crearConexion(); //ABRIR CONEXION
		
		//FORMATO FECHA
		SimpleDateFormat dfh = new SimpleDateFormat("HH:mm:ss");
		SimpleDateFormat dff = new SimpleDateFormat("dd/MM/yyyy");
		
		if(mongo != null){
			try{
				//SI NO EXISTE BASE DE DATOS, LA CREA
				MongoDatabase db = mongo.getDatabase(db_name);
				
				//ELIMINAR BASE DE DATOS
				logger.debug("START: " 	+ dfh.format(new Date()) + " hrs. " + dff.format(new Date()) + "\n - DROP DATA_BASE: " + db_name);
				db.drop();
				logger.debug("FINISHED: " + dfh.format(new Date()) + " hrs. " + dff.format(new Date()) + "\n - PROCESS COMPLETE");
						
			}catch(Exception e){
				logger.error(e);
			}
			
		}else{
			logger.warn("ERROR CONEXION MONGO_DB (DROP DATABASE)");
		}
	}
	
	/**********************************************************************************************************************************************/
	
	public int countCollection(MongoClient mongo, String db_name, String collection){
		Long count	=	0L;
		//MongoClient mongo		=	MongoCliente.crearConexion(); //ABRIR CONEXION
		if(mongo != null){
			//SI NO EXISTE BASE DE DATOS, LA CREA
			MongoDatabase db = mongo.getDatabase(db_name);
			
			//CREA TABLA SI NO EXISTE Y AGREGA DATOS
			MongoCollection<Document> table = db.getCollection(collection);
	        
	        //CANTIDAD DE DATOS EN LA TABLA
			count = table.countDocuments();
			
		}else{
			logger.warn("ERROR CONEXION MONGO_DB (COUNT COLLECTION)");
		}
		
		return count.intValue();
	}
	
	/**********************************************************************************************************************************************/
	
	public static void main(String args[]){
		deleteCollection("Biometrico");
	}
	
}
