import com.mongodb.ErrorCategory;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
      try {
        // Connect to MongoDB Server on localhost, port 27017 (default)
        final MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
        // Connect to Database "cartoon"
        final MongoDatabase database = mongoClient.getDatabase("cartoon");
        System.out.println("Successful database connection established. \n");

        //Insert a document into the "characters" collection.
        MongoCollection<Document> collection = database.getCollection("characters");

        // Delete the collection and start fresh
        collection.drop();

        Document mickeyMouse = new Document();
        Document charlieBrown = new Document();
        Document student = new Document();

        mickeyMouse.append("_id", 1)
                .append("characterName", "Mickey Mouse")
                .append("creator", new Document("firstName", "Walt").append("lastName", "Disney"))
                .append("pet", "Goofy");

        charlieBrown.append("_id", 2)
                .append("characterName", "Charlie Brown")
                .append("creator", new Document("firstName", "Charles").append("lastName", "Shultz"))
                .append("pet", "Snoopy");

        student.append("_id",3)
        .append("name", "saurabh")
        .append("year", "SE");

        try {

                    // InsertOne

            collection.insertOne(mickeyMouse);        
            collection.insertOne(charlieBrown);
            collection.insertOne(student);
            System.out.println("Successfully inserted documents. \n");
        } catch (MongoWriteException mwe) {
            if (mwe.getError().getCategory().equals(ErrorCategory.DUPLICATE_KEY)) {
                System.out.println("Document with that id already exists");
            }
        }

        // Basic data on collection
        System.out.println("Collection size: " + collection.count() + " documents. \n");

        // Create and insert multiple documents
        List<Document> documents = new ArrayList<Document>();
        for (int i = 4; i < 51; i++) {
            documents.add(new Document ("_id", i)
                    .append("characterName", "")
                    .append("creator", "")
                    .append("pet", "")
            );
        }

                // InsertMany

        collection.insertMany(documents);

        // Basic data on collection
        System.out.println("Collection size: " + collection.count() + " documents. \n");

        // Update a document
        // print the third document before update.
        Document third = collection.find(Filters.eq("_id", 3)).first();
        System.out.println("Original third document:");
        System.out.println(third.toJson());

                    // Update document

        // collection.updateOne(new Document("_id", 3),
        //         new Document("$set", new Document("YOG", "2023")
        //                 .append("branch", "CS")
        //                 .append("year", "TE"))
        // );

        System.out.println("\nUpdated third document:");
        Document dilbert = collection.find(Filters.eq("_id", 3)).first();
        System.out.println(dilbert.toJson());

                   // Find and print ALL documents in the collection

        System.out.println("Print the documents.");

        MongoCursor<Document> cursor = collection.find().iterator();
        try {
            while (cursor.hasNext()) {
                System.out.println(cursor.next().toJson());
            }

        } finally {
            cursor.close();
        }

                   // Delete data

        System.out.println("\nDelete documents with an id greater than or equal to 4.");
        collection.deleteMany(Filters.gte("_id", 4));

                   // Find and print ALL documents in the collection

        System.out.println("\nPrint all documents.");

        MongoCursor<Document> cursor2 = collection.find().iterator();
        try {
            while (cursor2.hasNext()) {
                System.out.println(cursor2.next().toJson());
            }

        } finally {
            cursor2.close();
        }

    } catch (Exception exception) {
        System.err.println(exception.getClass().getName() + ": " + exception.getMessage());
    }
}
}
