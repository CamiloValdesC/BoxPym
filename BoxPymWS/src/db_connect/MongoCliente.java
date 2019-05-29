package db_connect;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoIterable;

public class MongoCliente {
	
static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(MongoCliente.class);
	
	public MongoCliente(){
		//crearConexion();
	}
	
	//CREAR CONEXION A MONGO_DB
	public static MongoClient crearConexion(){
		MongoClient mongo = null;
		
		try{
			//DESACTIVAR LOG AUTOMATICO DRIVER MONGODB
			Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
			mongoLogger.setLevel(Level.SEVERE);
			
			//CONFIG CREDENCIALES MONGODB
			//List<MongoCredential> creds = new ArrayList<MongoCredential>();
			//creds.add(MongoCredential.createCredential(username, DBname, password);
			
			//CONFIG OPTIONES VARIAS DRIVER MONGODB
			MongoClientOptions.Builder optionsBuilder = MongoClientOptions.builder();
			optionsBuilder.connectTimeout(1500);
			optionsBuilder.socketTimeout(1500);
			optionsBuilder.serverSelectionTimeout(1500);
			MongoClientOptions options = optionsBuilder.build();
			
			//CONECTAR A MONGODB
			String 	server		=	"127.0.0.1";
			int		portServer	=	27017;			
					mongo 		=	new MongoClient(new ServerAddress(server , portServer), options);
			//						new MongoClient(new ServerAddress(server , port), creds, options);
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
