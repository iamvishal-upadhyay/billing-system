package com.cisco.brm;

import java.util.Scanner;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cisco.brm.beans.CustomerBean;
import com.cisco.brm.beans.CustomerAddressBean;
import com.cisco.brm.util.MongoDBUtility;

public class BillingMain {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(BillingMain.class);

    private static MongoDBUtility mongoDBUtility = new MongoDBUtility();

    public static void main(String[] args) {
        LOGGER.info("Execution Start.");
        
        String databaseName = "dev";
        String collectionName = "customer";
	CustomerBean customer = new CustomerBean();

        try {
            MongoClient mongoClient = mongoDBUtility.connect();
	    MongoDatabase database = mongoDBUtility.connectToDatabase(databaseName);
	    MongoCollection collection = mongoDBUtility.connectToCollection(collectionName);

	    // create a scanner so we can read the command-line input
            Scanner scanner = new Scanner(System.in);

            //  prompt for the user's name
            LOGGER.info("Enter your name: ");

            // get their input as a String
            String username = scanner.nextLine();
            customer.setName(username);

            //  prompt for the user's name
            LOGGER.info("Enter your id: ");

            // get their input as a String
            String id = scanner.next();
	    customer.setId(id);

/*	    // create a record / document to store key and value
            Document customer = new Document("_id", "vishal")
                                     .append("name", "Vishal Upadhyay")
                                     .append("address", new Document("street", "Kammagondanahalli Main Street")
                                                             .append("city", "Bangalore")
                                                             .append("state", "Karnataka")
                                                             .append("zip", 560015));

            /* Insert a record into the collection */
      /*      collection.insertOne(customer); */
         
            /* Find the record */
	    BasicDBObject searchQuery = new BasicDBObject();
	    searchQuery.put("_id", customer.getId());

	    MongoCursor<Document> cursor = collection.find(searchQuery).iterator();  
	
            if (cursor.hasNext()) {
                LOGGER.trace("Record with id : " + customer.getId() + " already present.");

                try {
                    while (cursor.hasNext()) {
                        LOGGER.info(cursor.next().toJson());
                    }
                } finally {
                    cursor.close();
                }
            } else {
                LOGGER.trace("Record with id : " + customer.getId() + " created.");
                // create a record / document to store key and value
                Document customerDocument = new Document("_id", customer.getId())
                                                 .append("name", customer.getName());

                /* Insert a record into the collection */
                collection.insertOne(customerDocument);
                /* Find the record */
	        BasicDBObject searchQuery1 = new BasicDBObject();
	        searchQuery1.put("_id", customer.getId());

	        MongoCursor<Document> cursor1 = collection.find(searchQuery1).iterator();  
                try {
                    while (cursor1.hasNext()) {
                        LOGGER.info(cursor1.next().toJson());
                    }
                } finally {
                    cursor.close();
                } 
            }
        } catch (MongoWriteException mongoWriteException) {
	    LOGGER.error("Exception : {}", mongoWriteException);
        } catch (MongoException mongoException) {
	    LOGGER.error("Exception : {}", mongoException);
        } finally {
            mongoDBUtility.disconnect();
	    LOGGER.info("Execution finished.");
        }
    }
}
