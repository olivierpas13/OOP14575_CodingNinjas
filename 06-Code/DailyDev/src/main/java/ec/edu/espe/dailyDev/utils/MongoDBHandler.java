package ec.edu.espe.dailyDev.utils;

import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;
import io.github.cdimascio.dotenv.Dotenv;
import org.bson.Document;

/**
 * Clase que maneja la conexión y operaciones con MongoDB.
 *
 * @author Olivier Paspuel
 */

public class MongoDBHandler {

    private MongoClient mongoClient;

    public MongoDBHandler() {
        this.mongoClient = connect();
    }

    public MongoClient connect() {
        Dotenv dotenv = Dotenv.load();
        String MONGODB_URI = dotenv.get("MONGODB_URI");

        MongoClient mongoClient = MongoClients.create(MONGODB_URI);
        return mongoClient;
    }

    public MongoCollection<Document> connectToCollection(String collectionName) {
        MongoDatabase db = mongoClient.getDatabase("DailyDevDB");
        MongoCollection<Document> collection = db.getCollection(collectionName);
        return collection;
    }

    public void createDocument(String collectionName, Document document) {
        MongoCollection<Document> collection = connectToCollection(collectionName);

        try {
            InsertOneResult result = collection.insertOne(document);
            System.out.println("Success! Inserted document id: " + result.getInsertedId());

        } catch (MongoException me) {
            System.err.println("Unable to insert due to an error: " + me);
        }
    }

    public boolean documentExists(String collectionName, Document query) {
        MongoCollection<Document> collection = connectToCollection(collectionName);
        return collection.find(query).limit(1).iterator().hasNext();
    }

    public boolean checkCredentials(String username, String password, String collectionName) {
        try {
            MongoDatabase database = mongoClient.getDatabase("DailyDevDB");
            MongoCollection<Document> collection = database.getCollection(collectionName);

            Document query = new Document("username", username)
                    .append("password", PasswordHandler.encryptPassword(password));

            FindIterable<Document> result = collection.find(query);

            return result.iterator().hasNext();
        } catch (Exception e) {
            System.err.println("Error checking credentials in MongoDB: " + e.getMessage());
            return false;
        }
    }
}
