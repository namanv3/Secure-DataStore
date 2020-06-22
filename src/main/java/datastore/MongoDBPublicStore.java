package datastore;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Iterator;

public class MongoDBPublicStore implements IPublicStore {
    private final MongoCollection<Document> collection;

    public MongoDBPublicStore (String url, int port, String platform) {
        MongoClient mongoClient = new MongoClient(url, port);
        MongoDatabase database = mongoClient.getDatabase(platform);
        collection = database.getCollection("PublicStore");
    }

    @Override
    public void publicStoreSet(String key, byte[] data) {
        Document document = new Document();
        document.put("key",key);
        document.put("value",data);
        collection.insertOne(document);
    }

    @Override
    public byte[] publicStoreGet(String key) {
        Document document = new Document();
        document.put("key",key);
        FindIterable<Document> doc = collection.find(document);
        Iterator<Document> iterator = doc.iterator();
        Document result = iterator.next();
        return (byte[]) result.get("value");
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
//    public MongoDBPublicStore(String platform) {
//        MongoClient mongoClient = new MongoClient("localhost",27017);
//        DB db = mongoClient.getDB(platform);
//        collection = db.getCollection("PublicStore");
//    }
//
//    @Override
//    public void publicStoreSet(String key, byte[] data) {
//        DBObject block = new BasicDBObject("key", key).append("value",data);
//        collection.insert(block);
//    }
//
//    @Override
//    public byte[] publicStoreGet(String key) {
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
