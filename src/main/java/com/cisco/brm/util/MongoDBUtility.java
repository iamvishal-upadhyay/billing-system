package com.cisco.brm.util;

import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MongoDBUtility {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(MongoDBUtility.class);

    /* initialize connection object */
    private MongoClient mongoClient;
    
    /* initialize database object */
    private MongoDatabase mongoDatabase;

    /* initialize collection / table object */
    private MongoCollection collection;
    
    /**
     * Method is responsible for connecting to the MongoDB instance.
     * 
     * @return
     */
    public MongoClient connect() {
        if (mongoClient == null) {
            try {
                /* connect to MongoDB instance running on localhost port:27017 */
	        mongoClient = new MongoClient();	
            } catch (MongoException mongoException) {
                LOGGER.error("Connection to the MongoDB instance failed.");
                LOGGER.error("Exception : {}", mongoException);
            }
        }
        return mongoClient;
    }

    /**
     * Method is responsible for closing the connection to the MongoDB instance.
     */
    public void disconnect() {
        if (mongoClient != null) {
            try {
                mongoClient.close();
                mongoClient = null;
            } catch (MongoException mongoException) {
                LOGGER.error("Exception when trying to close the connection : {}", mongoException);
            }
        }
    }

    /**
     * Method is responsible for connecting to the MongoDB database.
     * 
     * @return
     */
    public MongoDatabase connectToDatabase(String databaseName) {
        if (mongoClient != null && mongoDatabase == null) {
            /* get database - if database doesn't exists, MongoDB creates it for you */
	    mongoDatabase = mongoClient.getDatabase(databaseName);			
        }
        return mongoDatabase;
    }

    /**
     * Method is responsible for connecting to the MongoDB collection.
     * 
     * @return
     */
    public MongoCollection connectToCollection(String collectionName) {
        if (mongoClient != null && mongoDatabase != null && collection == null) {
            /* Get collection / table from the database - if collection doesn't exists, MongoDB creates it for you */
	    collection = mongoDatabase.getCollection(collectionName);			
        }
        return collection;
    }
}
