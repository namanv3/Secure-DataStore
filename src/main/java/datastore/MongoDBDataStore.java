package datastore;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.Binary;

import java.util.Iterator;

public class MongoDBDataStore implements IDataStore {
    private final MongoCollection<Document> collection;

    public MongoDBDataStore(String url, int port, String platform) {
        MongoClient mongoClient = new MongoClient(url, port);
        MongoDatabase database = mongoClient.getDatabase(platform);
        collection = database.getCollection("DataStore");
    }

    @Override
    public void dataStoreSet(String key, byte[] data) {
        Document document = new Document();
        document.put("key",key);
        document.put("value",data);
        collection.insertOne(document);
    }

    @Override
    public byte[] dataStoreGet(String key) {
        Document document = new Document();
        document.put("key",key);
        FindIterable<Document> doc = collection.find(document);
        Iterator<Document> iterator = doc.iterator();
        Document result = iterator.next();
        Binary ans = (Binary) result.get("value");
        return ans.getData();
    }

    @Override
    public boolean isPresent(String key) {
        Document document = new Document();
        document.put("key",key);
        FindIterable<Document> doc = collection.find(document);
        Iterator<Document> iterator = doc.iterator();
        return iterator.hasNext();
    }

//    private final DBCollection collection;
//
//    public MongoDBDataStore(String platform) {
//        MongoClient mongoClient = new MongoClient("localhost",27017);
//        DB db = mongoClient.getDB(platform);
//        collection = db.getCollection("DataStore");
//    }
//
//    @Override
//    public void dataStoreSet(String key, byte[] data) {
//        DBObject block = new BasicDBObject("key", key).append("value",data);
//        collection.insert(block);
//    }
//
//    @Override
//    public byte[] dataStoreGet(String key) {
//        DBObject query = new BasicDBObject("key",key);
//        DBCursor cursor = collection.find(query);
//        DBObject object = cursor.one();
//        return (byte[]) object.get("value");
//    }
//
//    @Override
//    public boolean isPresent(String key) {
//        DBObject query = new BasicDBObject("key",key);
//        DBCursor cursor = collection.find(query);
//        return cursor.hasNext();
//    }
}
