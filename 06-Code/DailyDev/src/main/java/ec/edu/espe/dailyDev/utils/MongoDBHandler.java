package ec.edu.espe.dailyDev.utils;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
<<<<<<< Updated upstream
import com.mongodb.client.FindIterable;
=======
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
>>>>>>> Stashed changes
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.result.InsertOneResult;
import io.github.cdimascio.dotenv.Dotenv;
import org.bson.Document;
<<<<<<< Updated upstream
=======
import org.bson.UuidRepresentation;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
>>>>>>> Stashed changes

/**
 * Clase que maneja la conexión y operaciones con MongoDB.
 *
 * @author Olivier Paspuel
 */

public class MongoDBHandler {

    public MongoDatabase connect() {
        Dotenv dotenv = Dotenv.load();
        CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
        CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);

        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();

        MongoClientSettings clientSettings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(dotenv.get("MONGODB_URI")))
                .serverApi(serverApi)
                .codecRegistry(codecRegistry)
                .uuidRepresentation(UuidRepresentation.STANDARD)
                .build();

        MongoClient mongoClient = MongoClients.create(clientSettings);
        MongoDatabase db = mongoClient.getDatabase("DailyDevDB");

        return db;
    }

    public MongoCollection<Document> connectToCollection(String collectionName) {
        MongoDatabase db = connect();
        MongoCollection<Document> collection = db.getCollection(collectionName);
        return collection;
    }

    public void createDocument(String collectionName, Document document) {
        MongoCollection<Document> collection = connectToCollection(collectionName);

        try {
            InsertOneResult result = collection.insertOne(document);
            System.out.println("Succesfully created!\n");

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
    public Document findOneDoc(String key, Object value, String collectionName) {

        MongoCollection<Document> collection = connectToCollection(collectionName);

        return collection.find(eq(key, value)).first();

    }
}

//    @Override
//    public String read(String filterKey, String filterValue, String table) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    @Override
//    public String readAll(String table) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    @Override
//    public boolean update(String filterKey, String filterValue, String newData, String table) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    @Override
//    public boolean delete(String filterKey, String filterValue, String table) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }

