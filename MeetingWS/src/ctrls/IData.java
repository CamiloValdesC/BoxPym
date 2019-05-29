package ctrls;

import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.bson.Document;

import com.mongodb.WriteResult;

public interface IData {

	List<Object> getCollection();
	
	List<Document> getCollectionFind(Set<Entry<String, Object>> set);
	
	WriteResult insertNewDocCollection(List<Object> lista);
	
}