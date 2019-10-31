package com.nikthedev.mongo;


import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.nikthedev.model.SettingsPerUser;

public class MongoUtils {
	
	private static final Logger log = LoggerFactory.getLogger(MongoUtils.class);
	
	private static MongoClient client;
	
	private MongoUtils() {
		//empty
	}
	
	public static  MongoClient getConnection() {
        if (client == null) {
        	
        	
            MongoClientOptions.Builder options = MongoClientOptions.builder().connectionsPerHost(10)
                    .maxConnectionIdleTime((60 * 1000)).maxConnectionLifeTime((120 * 1000))
                    .codecRegistry(withRegitstry());
            MongoClientURI uri = new MongoClientURI("mongodb://"+System.getenv("WSO2_SERVER")+":27017", options);
            try {
                client = new MongoClient(uri);
                
            } catch (Exception ex) {
            	
                log.error("Error-MongoDB-getConnection(): {}", ex);
            }
        }
        return client;
    }

    
    private static CodecRegistry withRegitstry() {
    	return fromRegistries(MongoClient.getDefaultCodecRegistry(),
    			fromProviders(PojoCodecProvider.builder().automatic(true).build()));
    }
	
    
  //Get Mongo Settings collection
  	public static MongoCollection<SettingsPerUser> getCollection(MongoClient client) {
  		return  client.getDatabase("webAppDB")
  				.getCollection("settings", SettingsPerUser.class)
  				.withCodecRegistry(withRegitstry());
  	}


}
