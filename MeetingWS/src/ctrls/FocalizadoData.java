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
import com.mongodb.client.MongoDatabase;

import dataBase.MongoCliente;
import model.Focalizado;

public class FocalizadoData implements IData{
	
	public 	static final Logger logger		=	Logger.getLogger(PersonaData.class);
	private static final String	DBNAME 		= 	"Biometrico";
	private static final String	COLLECTION 	= 	"Matricula";	

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
				int limit = 5000000;
		        logger.debug("FindIterable " + limit);
		        FindIterable<Document> docs = table.find().limit(limit);
		        for(Document doc : docs){
		        	String 	mes_sige 	=	doc.getString("MES_SIGE");
		        	String 	run			=	doc.getString("RUN");
		        	String	sal_dgv		=	doc.getString("SAL_DGV");
		        	String	nombres		=	doc.getString("NOMBRES");
		        	String	ap_pat		=	doc.getString("AP_PAT");
		        	String	ap_mat		=	doc.getString("AP_MAT");
		        	//SI EXISTE DATA CARGA LISTA
		        	data.add(new Focalizado(mes_sige, run, sal_dgv, nombres, ap_pat, ap_mat));
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
		// TODO Auto-generated method stub
		return null;
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
		        	Focalizado  focalizado		=	(Focalizado) object;
		        	Document document			=	new Document();
		        	document.put("MES_SIGE"		,	focalizado.getMes_sige());
		        	document.put("RUN"			,	focalizado.getRun());
		        	document.put("SAL_DGV"		,	focalizado.getSal_dgv());
		        	document.put("NOMBRES"		,	focalizado.getNombres());
		        	document.put("AP_PAT"		,	focalizado.getAp_pat());
		        	document.put("AP_MAT"		,	focalizado.getAp_mat());
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
