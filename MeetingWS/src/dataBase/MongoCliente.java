package dataBase;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoIterable;

public class MongoCliente {
	
	static Logger log = Logger.getLogger(MongoCliente.class);
		
	public MongoCliente(){
		//crearConexion();
		
		Logger logMongoDB = Logger.getLogger("org.mongodb");
		logMongoDB.setLevel(Level.OFF);
	}
	
	//CREAR CONEXION A MONGO_DB
	public static MongoClient crearConexion(){
		MongoClient mongo = null;
		try{
			mongo = new MongoClient("127.0.0.1", 27017);
		}catch(Exception ex){
			log.error(ex);
		}
		return mongo;
    }
	
	//IMPRIME TODAS LAS BASE EXISTENTES
	private static void printDatabases(MongoClient mongo){
		try{
	        MongoIterable<String> dbs = mongo.listDatabaseNames();
	        for (String db : dbs) {
	            System.out.println(" - " + db);
	        }
		}catch(Exception ex){
			log.error(ex);
		}
    }

	//TESTING
	public static void main(String[] args){
		MongoClient mongo = crearConexion();
		if(null != mongo){
			printDatabases(mongo);
		}
	}

}
