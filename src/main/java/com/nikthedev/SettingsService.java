/*
 * Copyright (c) 2016, WSO2 Inc. (http://wso2.com) All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.nikthedev;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.List;

import org.bson.conversions.Bson;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.FindOneAndReplaceOptions;
import com.mongodb.client.model.ReturnDocument;
import com.nikthedev.model.SettingsPerUser;
import com.nikthedev.mongo.MongoUtils;


/**
 * This is the Microservice resource class.
 * See <a href="https://github.com/wso2/msf4j#getting-started">https://github.com/wso2/msf4j#getting-started</a>
 * for the usage of annotations.
 *
 * @since 0.1
 */
public class SettingsService {

	private static final String HEX_ID = "hexId";

	public  SettingsPerUser create(SettingsPerUser model) {
		
		MongoClient client = MongoUtils.getConnection();
		MongoCollection<SettingsPerUser> collection = MongoUtils.getCollection(client);
		collection.insertOne(model);
		return model;
	}

	public List<SettingsPerUser> find(SettingsPerUser model) {
		
		MongoClient client = MongoUtils.getConnection();
		MongoCollection<SettingsPerUser> collection = MongoUtils.getCollection(client);
		List<SettingsPerUser> results = new ArrayList<>();		
		return collection.find(findCriteria(model)).into(results);
	}

	public SettingsPerUser update(SettingsPerUser model) {
	
		MongoClient client = MongoUtils.getConnection();
		MongoCollection<SettingsPerUser> collection = MongoUtils.getCollection(client);
		FindOneAndReplaceOptions options = new FindOneAndReplaceOptions();
		options.upsert(false);
		options.returnDocument(ReturnDocument.AFTER);
		return collection.findOneAndReplace(eq(HEX_ID, model.getHexId()), model, options);

	}

	public SettingsPerUser delete(SettingsPerUser model) {
		
		BasicDBObject criteria = criteria(model);
		MongoClient client = MongoUtils.getConnection();
		MongoCollection<SettingsPerUser> collection = MongoUtils.getCollection(client);
		return collection.findOneAndDelete(criteria);
	}
	
	private BasicDBObject criteria(SettingsPerUser model) {
		BasicDBObject criteria = new BasicDBObject();
		if (model.getHexId()!=null && model.getHexId().isEmpty()) {
			criteria.append(HEX_ID, model.getHexId());
		}
		return criteria;
	}
	
	
	private Bson findCriteria(SettingsPerUser model) {
		Bson filter = Filters.and();
		if (model.getHexId() != null && !model.getHexId().isEmpty()) {
			filter = and(eq(HEX_ID, model.getHexId()));
		}
		return filter;
	}

}
