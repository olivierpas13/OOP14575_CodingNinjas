package ec.edu.espe.dailyDev.utils;

import com.google.gson.Gson;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.currentDate;
import static com.mongodb.client.model.Updates.set;
import com.mongodb.client.result.InsertOneResult;
import ec.edu.espe.dailyDev.model.Task;
import io.github.cdimascio.dotenv.Dotenv;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

import org.bson.UuidRepresentation;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;

/**
 * Clase que maneja la conexión y operaciones con MongoDB.
 *
 * @author Olivier Paspuel
 */
public class MongoDBHandler {

    public static MongoDatabase connect() {
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

    public static MongoCollection<Document> connectToCollection(String collectionName) {
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
//            MongoDatabase database = mongoClient.getDatabase("DailyDevDB");
//            MongoCollection<Document> collection = database.getCollection(collectionName);

            MongoCollection<Document> collection = connectToCollection(collectionName);

            Document query = new Document("username", username)
                    .append("password", PasswordHandler.encryptPassword(password));

            FindIterable<Document> result = collection.find(query);

            return result.iterator().hasNext();
        } catch (Exception e) {
            System.err.println("Error checking credentials in MongoDB: " + e.getMessage());
            return false;
        }
    }

    public Document findOneDoc(String key, Object value, String collectionName) {

        MongoCollection<Document> collection = connectToCollection(collectionName);

        return collection.find(eq(key, value)).first();

    }

    public Document findDocWithFilter(Bson filter, String collectionName) {
        MongoCollection<Document> collection = connectToCollection(collectionName);
        return collection.find(filter).first();
    }

    public static FindIterable<Document> findAllDocs(Bson filter, String collectionName) {
        MongoCollection<Document> collection = connectToCollection(collectionName);
        return collection.find(filter);
    }

    public static List<Task> findAllTasks(Bson filter) {
//        MongoCollection<Document> collection = connectToCollection(collectionName);
        FindIterable<Document> documents = findAllDocs(filter, "Tasks");

        System.out.println(documents);
        List<Task> taskList = new ArrayList<>();

        for (Document document : documents) {
            // Convert each Document to a Task object
            Task task = convertDocumentToTask(document);

            // Add the Task object to the list
            taskList.add(task);
        }

        return taskList;
    }

    private static Task convertDocumentToTask(Document document) {
        // Implement logic to convert Document to Task object
        Gson gson = new Gson();

        String task = gson.toJson(document);

        return gson.fromJson(task, Task.class);
        // Example: return new Task(document.getString("taskId"), document.getString("taskName"), ...);
    }

    public static Document updateDocument(Document query, Bson updates) {
        MongoCollection<Document> collection = connectToCollection("Tasks");
        try {
            Document updatedDocument = collection.findOneAndUpdate(query, updates,
                    new FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER));

            System.out.println("Document updated successfully");
            return updatedDocument;
        } catch (MongoException me) {
            System.err.println("Unable to update due to an error: " + me);
            return null;
        }
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

